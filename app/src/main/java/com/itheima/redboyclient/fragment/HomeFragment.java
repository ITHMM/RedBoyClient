package com.itheima.redboyclient.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.DB.SpHelp;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.HomeBannerPagerAdapter;
import com.itheima.redboyclient.adapter.HomeListBaseAdapter;
import com.itheima.redboyclient.bean.HomeResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xch on 2016/3/30.
 * 首页的填充页面
 */
public class HomeFragment extends BaseFragment implements HttpLoader.ResponseListener {
    private static final String TAG = "HomeFragment";
    /**
     * 搜索的输入框
     */
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    /**
     * 搜索按钮
     */
    @InjectView(R.id.ok)
    ImageView ok;
    /**
     * 首页搜索栏的布局
     */
    @InjectView(R.id.relSearch)
    RelativeLayout relSearch;
    /**
     * 首页上方的viewpager
     */
    @InjectView(R.id.vp)
    ViewPager vp;
    /**
     * viewpaer中的圆点指示器
     */
    @InjectView(R.id.imgPoint)
    ImageView imgPoint;
    /**
     * 首页的listview
     */
    @InjectView(R.id.custonInfoListView)
    ListView custonInfoListView;

    @InjectView(R.id.orderTelTv)
    TextView orderTelTv;
    @InjectView(R.id.titleText)
    /**
     * 登录按钮
     */
            TextView titleText;
    @InjectView(R.id.loginIv)
    /**
     * 登录之后变化的图片
     */
            ImageView loginIv;
    @InjectView(R.id.imgAvataIcon)
    ImageView imgAvataIcon;
    /**
     * 首页上方标题栏的布局
     */
    @InjectView(R.id.relTop)
    RelativeLayout relTop;
    private Handler handler;


    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }
    @Override
    public void initData() {

        //判断是否登录
        if(new SpHelp().getsp("login",false)){//已经登录
            //把登录隐藏
            loginIv.setVisibility(View.GONE);

        }
        // 获取首页的网络数据
        HttpLoader.get(ConstantsRedBaby.URL_HOME, null, HomeResponse.class, ConstantsRedBaby.REQUEST_CODE_HOME, this, true);
        // 给listview设置一个适配器
        custonInfoListView.setAdapter(new HomeListBaseAdapter());
        /**
         * listview条目的点击事件
         */
        custonInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (position) {

                    case 0:
                        ft.add(R.id.rl_home_fragment, new LimitBuyFragment());
                        ft.addToBackStack("HomeFragment");
                        ft.hide(HomeFragment.this);
                        ft.commit();
                        break;
                    case 1:
                        ft.add(R.id.rl_home_fragment, new PromFragment());
                        ft.addToBackStack("HomeFragment");
                        ft.hide(HomeFragment.this);
                        ft.commit();
                        break;
                    case 2:
                        ft.add(R.id.rl_home_fragment, new NewProductFragment());
                        ft.addToBackStack("HomeFragment");
                        ft.hide(HomeFragment.this);
                        ft.commit();
                        break;
                    case 3:
                        ft.add(R.id.rl_home_fragment, new HotProductFragment());
                        ft.addToBackStack("HomeFragment");
                        ft.hide(HomeFragment.this);
                        ft.commit();
                        break;
                    case 4:
                        ft.add(R.id.rl_home_fragment, new BrandFragment());
                        ft.addToBackStack("HomeFragment");
                        ft.hide(HomeFragment.this);
                        ft.commit();
                        break;

                }
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.rl_home_fragment, new SearchResultFragment());
                ft.addToBackStack("SearchResultFragment");
                ft.commit();
            }
        });
        orderTelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://010-88499999"));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    // 请求成功
    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        Log.i(TAG, "onGetResponseSuccess: " + response);
        HomeResponse homeResponse = (HomeResponse) response;
        // 初始化viewpager的适配器
        HomeBannerPagerAdapter pagerAdapter = new HomeBannerPagerAdapter(homeResponse,getActivity());
        // 给viewpager设置一个适配器
        vp.setAdapter(pagerAdapter);
        carousel();
    }
    private int oldState;
    /**
     * 轮播viewpager的图片
     */
    private void carousel() {
       if(handler == null){
           handler = new Handler(){
               @Override
               public void handleMessage(Message msg) {
                  if(vp != null){
                      int currentItem = vp.getCurrentItem();
                      ++currentItem;
                      if (currentItem > vp.getChildCount()+1){
                          currentItem =0;
                      }
                      vp.setCurrentItem(currentItem);
                      handler.sendEmptyMessageDelayed(0,3000);
                  }
               }
           };
           handler.sendEmptyMessageDelayed(0,3000);
           // viewpager的滑动变化监听
           vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

               }

               @Override
               public void onPageSelected(int position) {

               }

               @Override
               public void onPageScrollStateChanged(int state) {
                   ALog.i("stat:"+state);
                   // 1代表手指触摸上去,2代表手指离开
                   if(state == 1){
                       handler.removeCallbacksAndMessages(null);
                   }else if(state == 2 && oldState == 1){
                       handler.sendEmptyMessageDelayed(0,3000);
                   }
                   oldState = state;
               }
           });
            // 在触摸的时候处理轮播的bug
//           vp.setOnTouchListener(new View.OnTouchListener() {
//               @Override
//               public boolean onTouch(View v, MotionEvent event) {
//                   switch (event.getAction()){
//                       case MotionEvent.ACTION_DOWN: // 按下的时候清楚所有消息
//                           handler.removeCallbacksAndMessages(null);
//                           break;
//                       case MotionEvent.ACTION_CANCEL: // 中途移动到控件之外,取消触摸时间的时候触发
//                           handler.sendEmptyMessageDelayed(0,3000);
//                           break;
//                       case MotionEvent.ACTION_UP://手指离开屏幕的时候触发
//                           handler.sendEmptyMessageDelayed(0,3000);
//                           break;
//                   }
//                   return false;
//               }
//           });
       }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    // 请求失败
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @OnClick(R.id.loginIv)
    public void onClick() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rl_home_fragment, new LoginFragment());
        ft.addToBackStack("LoginFragment");
        ft.commit();
    }


        //by xiaoyan
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {  //界面隐藏，禁用所有点击事件
            if (custonInfoListView == null) {
                return;
            }
            for (int i = 0; i < custonInfoListView.getChildCount(); i++) {
                custonInfoListView.getChildAt(i).setEnabled(false); // 置为不可用
            }

        } else {//界面显示，相应所有点击事件

            for (int i = 0; i < custonInfoListView.getChildCount(); i++) {
                custonInfoListView.getChildAt(i).setEnabled(true); // 置为可用
            }
        }
    }
}
