package com.itheima.redboyclient.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.LoginResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.RegisterResponse;
import com.itheima.redboyclient.dao.UsersInfosDao;
import com.itheima.redboyclient.dao.UsersInfosHelper;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterFragment extends BaseFragment implements HttpLoader.ResponseListener {


    @InjectView(R.id.register_email)
    EditText registerEmail;
    @InjectView(R.id.register_password)
    EditText registerPassword;
    @InjectView(R.id.register_rpassword)
    EditText registerRpassword;
    private String email;
    private String password2;
    private String password;
    private SQLiteDatabase db;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.register_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


    @OnClick({R.id.refister_text})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.refister_text://注册
                email = registerEmail.getText().toString().trim();
                password = registerPassword.getText().toString().trim();
                password2 = registerRpassword.getText().toString().trim();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password2) || TextUtils.isEmpty(password)) {
                    Toast.makeText(mActivity, "请输入数据！", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password2)) {
                    Toast.makeText(mActivity, "两次密码不致！", Toast.LENGTH_SHORT).show();
                } else if (password.length()<6) {
                    Toast.makeText(mActivity, "请输入至少六位以上密码！", Toast.LENGTH_SHORT).show();
                } else {
                    UsersInfosDao dao=new UsersInfosDao(mActivity) ;
                    String textpassword=dao.findUserinfo(email);
                    if(textpassword!=null){
                        Toast.makeText(mActivity, "账号已存在！", Toast.LENGTH_SHORT).show();

                    }else{
                        dao.addUser(email, password);//
                        skip(new LoginFragment());

                        // 网络请求
                        HashMap<String ,String > paraer=new HashMap<>();
                        paraer.put("username",email);
                        paraer.put("password",password);
                        HttpLoader.post(ConstantsRedBaby.URL_REGISTER, paraer, RegisterResponse.class,
                                ConstantsRedBaby.REQUEST_CODE_REGISTER, this, false);
                    }
                }
                break;
        }

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
                RegisterResponse info= (RegisterResponse) response;
                System.out.println("id:"+info.getUserId());
                break;
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
    }
}
