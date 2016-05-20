package com.itheima.redboyclient.activities;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.PromAdapter;
import com.itheima.redboyclient.bean.PromResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

/**
 * Created by 李正春 on 2016/3/30.
 */
public class PromBulletinActivity extends BaseActivity implements HttpLoader.ResponseListener, View.OnClickListener {
    private static final String HOME_FRAGMENT = "1";
    /**
     * 促销快报标题
     */
    private TextView mTextTitle;
    /**
     * 促销快报的ListView
     */
    private ListView mPromBulldtinLv;
    /**
     * 最下表的LinerLayout选项
     */
    private LinearLayout mBottomBar;
    /**
     * 促销快报头标题上的返回按钮
     */
    private TextView mHeaderBackBtn;
    /**
     * 热销快报的基类
     */
    private PromResponse proms;
    /**
     * 热销快报的Adapter
     */
    private PromAdapter adapter;
    /**
     * 屏幕的宽度
     */
    public static int mScreenWidth;
    /**
     * 屏幕的高度
     */
    public static int mScreenHeight;
    /**
     * 主界面按钮
     */
    private ImageView mImgHome;
    /**
     * 分类按钮
     */
    private ImageView mImgClassify;
    /**
     * 搜索按钮
     */
    private ImageView mImgSearch;
    /**
     * 购物车按钮
     */
    private ImageView mImgShoppingCar;
    /**
     * 更多按钮
     */
    private ImageView mImgMore;


    @Override
    protected int initContentView() {
        return R.layout.prom_bulletin_activity;
    }

    @Override
    protected void initView() {
        mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        mTextTitle = (TextView) findViewById(R.id.textTitle);
        mPromBulldtinLv = (ListView) findViewById(R.id.promBulldtinLv);
        mBottomBar = (LinearLayout) findViewById(R.id.bottomBar);
        mHeaderBackBtn = (TextView) findViewById(R.id.headerBackBtn);
        mImgHome = (ImageView) findViewById(R.id.imgHome);
        mImgHome.setSelected(true);
        mImgClassify = (ImageView) findViewById(R.id.imgClassify);
        mImgSearch = (ImageView) findViewById(R.id.imgSearch);
        mImgShoppingCar = (ImageView) findViewById(R.id.imgShoppingCar);
        mImgMore = (ImageView) findViewById(R.id.imgMore);
    }

    @Override
    protected void initListener() {
        mHeaderBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();//点击返回按钮关闭当前Activity
//                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
//                int container = R.id.fl_prom;
//                HomeFragment fragment = new HomeFragment();
//                ft.replace(container, fragment, HOME_FRAGMENT);
//                ft.commit();
            }
        });
    }

    @Override
    protected void initData() {
        mImgHome.setOnClickListener(this);
        mImgMore.setOnClickListener(this);
        mImgSearch.setOnClickListener(this);
        mImgShoppingCar.setOnClickListener(this);
        mImgClassify.setOnClickListener(this);
        //初始化数据,请求网络
        if (checkNetworked()) {//如果网络可用
            showProgressDialog();//弹出进度对话框
            //请求网络
            HttpLoader.get(ConstantsRedBaby.URL_PROM, null, PromResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPIC, this, true);
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        if (requestCode == ConstantsRedBaby.REQUEST_CODE_TOPIC) {
            proms = (PromResponse) response;
            if (adapter == null) {
                adapter = new PromAdapter(proms, getApplication(), getFragmentManager());
                mPromBulldtinLv.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
            dismissProgressDialog();
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        dismissProgressDialog();//请求失败取消对话框
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpLoader.cancelRequest(ConstantsRedBaby.REQUEST_CODE_TOPIC);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        switch (v.getId()) {
            case R.id.imgHome:// 首页
                mImgHome.setSelected(true);
                mImgClassify.setSelected(false);
                mImgSearch.setSelected(false);
                mImgShoppingCar.setSelected(false);
                mImgMore.setSelected(false);
                startActivity(intent);
                finish();
                break;
            case R.id.imgClassify:// 分类
                mImgHome.setSelected(false);
                mImgClassify.setSelected(true);
                mImgSearch.setSelected(false);
                mImgShoppingCar.setSelected(false);
                mImgMore.setSelected(false);
                startActivity(intent);
                finish();
                break;
            case R.id.imgSearch:// 搜索
                mImgHome.setSelected(false);
                mImgClassify.setSelected(false);
                mImgSearch.setSelected(true);
                mImgShoppingCar.setSelected(false);
                mImgMore.setSelected(false);
                startActivity(intent);
                finish();
                break;
            case R.id.imgShoppingCar:// 购物车
                mImgHome.setSelected(false);
                mImgClassify.setSelected(false);
                mImgSearch.setSelected(false);
                mImgShoppingCar.setSelected(true);
                mImgMore.setSelected(false);
                startActivity(intent);
                finish();
                break;
            case R.id.imgMore:// 更多
                mImgHome.setSelected(false);
                mImgClassify.setSelected(false);
                mImgSearch.setSelected(false);
                mImgShoppingCar.setSelected(false);
                mImgMore.setSelected(true);
                startActivity(intent);
                finish();
                break;
        }
    }
}
