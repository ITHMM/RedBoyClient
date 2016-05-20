package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.FavoriteResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.UserInfoResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/3/31/031.
 */
public class MyAccountFragment extends BaseFragment implements HttpLoader.ResponseListener {
    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.fraHead)
    FrameLayout fraHead;
    @InjectView(R.id.my_account_title)
    RelativeLayout myAccountTitle;
    @InjectView(R.id.my_name_text)
    TextView myNameText;
    @InjectView(R.id.my_name_lin)
    LinearLayout myNameLin;
    @InjectView(R.id.my_bonus_text)
    TextView myBonusText;
    @InjectView(R.id.my_bonus_lin)
    LinearLayout myBonusLin;
    @InjectView(R.id.my_level_text)
    TextView myLevelText;
    @InjectView(R.id.my_level_lin)
    LinearLayout myLevelLin;
    @InjectView(R.id.my_info)
    LinearLayout myInfo;
    @InjectView(R.id.loginOut_text)
    TextView loginOutText;
    @InjectView(R.id.imgHome)
    ImageView imgHome;
    @InjectView(R.id.imgClassify)
    ImageView imgClassify;
    @InjectView(R.id.imgSearch)
    ImageView imgSearch;
    @InjectView(R.id.imgShoppingCar)
    ImageView imgShoppingCar;
    @InjectView(R.id.imgMore)
    ImageView imgMore;
    @InjectView(R.id.linToolBar)
    LinearLayout linToolBar;
    @InjectView(R.id.textShopCarNum)
    TextView textShopCarNum;
    private UserInfoResponse userinfo;

    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.my_account_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_USERINFO, null, UserInfoResponse.class,
                ConstantsRedBaby.REQUEST_CODE_USERINFO, this, false);
    }

    @OnClick({R.id.head_back_text, R.id.loginOut_text})
    public void OnClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.head_back_text://返回  更多
                transaction.replace(R.id.rl_home_fragment, new MoreFragment());
                transaction.commit();
                break;
            case R.id.loginOut_text://退出账号 到登录页面
                transaction.replace(R.id.rl_home_fragment, new LoginFragment());
                transaction.commit();
                break;
        }
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_USERINFO:
                //得到数据
                userinfo = (UserInfoResponse) response;
                //设置数据的显示
                setInfo();
                break;
        }
    }

    private void setInfo() {
        //名称
        myNameText.setText("叶良辰");
        //会员 等级
        myLevelText.setText(userinfo.getUserinfo().getLevel());
        //积分
        myBonusText.setText(userinfo.getUserinfo().getBonus()+"");
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
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
}
