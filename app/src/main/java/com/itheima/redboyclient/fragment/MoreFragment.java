package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.UserInfoResponse;
import com.itheima.redboyclient.DB.SpHelp;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 更多
 * Created by Administrator on 2016/3/31/031.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener, HttpLoader.ResponseListener {


    @InjectView(R.id.my_ordr_text)
    TextView myOrdrText;
    @InjectView(R.id.my_order_rl)
    RelativeLayout myOrderRl;
    @InjectView(R.id.address_manage_text)
    TextView addressManageText;
    @InjectView(R.id.address_manage_rl)
    RelativeLayout addressManageRl;
    @InjectView(R.id.my_favorite_text)
    TextView myFavoriteText;
    @InjectView(R.id.my_favorite_rl)
    RelativeLayout myFavoriteRl;
    //  @InjectView(R.id.recent_browse_text)
    TextView recentBrowseText;
    // @InjectView(R.id.recent_browse_rl)
    RelativeLayout recentBrowseRl;
    @InjectView(R.id.helpRelLay)
    RelativeLayout helpRelLay;
    @InjectView(R.id.aboutRelLay)
    RelativeLayout aboutRelLay;
//    @InjectView(R.id.imgHome)
//    ImageView imgHome;
//    @InjectView(R.id.imgClassify)
//    ImageView imgClassify;
//    @InjectView(R.id.imgSearch)
//    ImageView imgSearch;
//    @InjectView(R.id.imgShoppingCar)
//    ImageView imgShoppingCar;
//    @InjectView(R.id.imgMore)
//    ImageView imgMore;
//    @InjectView(R.id.linToolBar)
//    LinearLayout linToolBar;
//    @InjectView(R.id.textShopCarNum)
//    TextView textShopCarNum;
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
    @InjectView(R.id.login_text)
    TextView loginText;
    @InjectView(R.id.register_text)
    TextView registerText;
    @InjectView(R.id.myFragment_notLoginLayout)
    LinearLayout myFragmentNotLoginLayout;
    @InjectView(R.id.bt_exit)
    ImageButton bt_exit;
    @InjectView(R.id.ll_more)
    LinearLayout ll_more;
    @InjectView(R.id.sl_ScrollView)
    ScrollView sl_ScrollView;
    @InjectView(R.id.ll_userinfo)
    LinearLayout ll_userinfo;
    private FragmentTransaction ft;
    private UserInfoResponse userinfo;
    private Boolean result;

    @Override
    public View CreateView() {
        result = new SpHelp().getsp("login", false);
        View view = View.inflate(mActivity, R.layout.more_activity, null);
        ButterKnife.inject(this, view);
        myFavoriteRl.setOnClickListener(this);
        myOrderRl.setOnClickListener(this);
        aboutRelLay.setOnClickListener(this);
        addressManageRl.setOnClickListener(this);
        helpRelLay.setOnClickListener(this);
        loginText.setOnClickListener(this);
        registerText.setOnClickListener(this);
        bt_exit.setOnClickListener(this);

        return view;
    }


    @Override
    public void initData() {
        //判断用户是登录
        result = new SpHelp().getsp("login", false);
        System.out.print("result" + result);
        if (!result) {//不在线 让用户信息消失 显示登录
            ll_userinfo.setVisibility(View.GONE);
            bt_exit.setVisibility(View.INVISIBLE);
        } else {//在线
            ll_userinfo.setVisibility(View.VISIBLE);
            bt_exit.setVisibility(View.VISIBLE);
            HttpLoader.get(ConstantsRedBaby.URL_USERINFO, null, UserInfoResponse.class,
                    ConstantsRedBaby.REQUEST_CODE_USERINFO, this, false);
        }


    }

    @Override
    public boolean onBackPressed() {
        if(getActivity()!=null){
            ((HomeActivity)getActivity()).imgHome.setSelected(true);
            ((HomeActivity)getActivity()).imgMore.setSelected(false);
        }
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
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.my_favorite_rl://收藏夹
                if (result) {//用户已登录
                    ft.add(R.id.rl_home_fragment, new MyFavoriteFragment());
                    ft.addToBackStack("MyFavoriteFragment");
                    ft.commit();
                } else {//用户没有登录 Toash
                    Toast.makeText(mActivity, "你还没有登录！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.my_order_rl://我的订单
                if (result) {//用户已登录
                    ft.add(R.id.rl_home_fragment, new OrderFragment());
                    ft.addToBackStack("OrderFragment");
                    ft.hide(this);
                    ft.commit();

                } else {//用户没有登录 Toash
                    Toast.makeText(mActivity, "你还没有登录！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.aboutRelLay://关于
                ft.add(R.id.rl_home_fragment, new AboutFragment());
                ft.addToBackStack("AboutFragment");
                ft.hide(this);
                ft.commit();
                break;
            case R.id.address_manage_rl://地址管理
                if (result) {//用户已登录
                    ft.add(R.id.rl_home_fragment, new AddresslistFrament());
                    ft.addToBackStack("AddresslistFrament");
                    ft.hide(this);
                    ft.commit();
                } else {//用户没有登录 Toash
                    Toast.makeText(mActivity, "你还没有登录！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.helpRelLay://帮助中心
                ft.add(R.id.rl_home_fragment, new HelpFragment());
                ft.addToBackStack("HelpFragment");
                ft.hide(this);
                ft.commit();
                break;
            case R.id.login_text://登录
                ft.add(R.id.rl_home_fragment, new LoginFragment());
                ft.addToBackStack("LoginFragment");
                ft.commit();
                break;
            case R.id.register_text://注册
                ft.add(R.id.rl_home_fragment, new RegisterFragment());
                ft.addToBackStack("RegisterFragment");
                ft.commit();
                break;
            case R.id.bt_exit://注销
                new SpHelp().setsp("login",false);
                ft.add(R.id.rl_home_fragment, new MoreFragment());
                ft.addToBackStack("MoreFragment");
                ft.commit();
                break;


            //其它的点击 事件 自行实现


        }
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
        String textusername=new SpHelp().getsp("username", "叶良辰");
        if (textusername.equals("叶良辰")){

            //名称
            myNameText.setText("叶良辰");
            //会员 等级
            myLevelText.setText(userinfo.getUserinfo().getLevel());
            //积分
            myBonusText.setText(userinfo.getUserinfo().getBonus() + "");
        }else{
            //名称
            myNameText.setText(textusername);
            //会员 等级
            myLevelText.setText("银卡");
            //积分
            myBonusText.setText(0+ "");
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("++onPause++++++++++++");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(hidden){  //界面隐藏，禁用所有点击事件
            for (int i = 0; i < ll_more.getChildCount(); i++) {
                ll_more.getChildAt(i).setEnabled(false); // 置为不可用
            }

        }else {//界面显示，相应所有点击事件
            for (int i = 0; i < ll_more.getChildCount(); i++) {
                ll_more.getChildAt(i).setEnabled(true); // 置为可用
            }
        }
    }

}
