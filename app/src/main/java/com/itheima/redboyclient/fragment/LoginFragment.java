package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.LoginResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.dao.UsersInfosDao;
import com.itheima.redboyclient.DB.SpHelp;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginFragment extends BaseFragment implements HttpLoader.ResponseListener {
    @InjectView(R.id.login_name_edit)
    EditText loginNameEdit;
    @InjectView(R.id.login_pwd_edit)
    EditText loginPwdEdit;

    private String username;
    private String password;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.login_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.login_text,R.id.register_text,R.id.bacck_home})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.login_text://登录
                username = loginNameEdit.getText().toString().trim();
                password = loginPwdEdit.getText().toString().trim();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password) ){
                    Toast.makeText(mActivity, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    //网络请求
                    HashMap<String ,String >paraer=new HashMap<>();
                    paraer.put("username",username);
                    paraer.put("password",password);
                    HttpLoader.post(ConstantsRedBaby.URL_LOGIN, paraer, LoginResponse.class,
                            ConstantsRedBaby.REQUEST_CODE_LOGIN, this, false);
                }

                break;
            case R.id.register_text://注册
                skip(new RegisterFragment());
                break;
            case R.id.bacck_home://返回首页
                skip(new HomeFragment());
                break;
        }

    }
    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_LOGIN:
                System.out.println("response=" + response);
                LoginResponse info= (LoginResponse) response;
                System.out.println("id:" + info.getUserId());

                UsersInfosDao dao=new UsersInfosDao(mActivity) ;
                String textpassword=dao.findUserinfo(username);
                if(textpassword!=null){

                    if(textpassword.equals(password)){//登录成功 返回首页
                        new SpHelp().setsp("login", true);
                        new SpHelp().setsp("username", username);

                        skip(new HomeFragment());
                    }else {
                        Toast.makeText(mActivity, "密码或账号不正确", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(mActivity, "密码或账号不正确", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
