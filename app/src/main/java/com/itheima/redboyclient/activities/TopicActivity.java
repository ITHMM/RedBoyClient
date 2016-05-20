package com.itheima.redboyclient.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.TopicAdapter;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.TopicResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.MyToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 主页
 */
public class TopicActivity extends BaseActivity implements HttpLoader.ResponseListener {


    @InjectView(R.id.mylimitbuy_product_list)
    ListView mMylimitbuyProductList;
    @InjectView(R.id.backTv)
    TextView backTv;
    @InjectView(R.id.textTitle)
    TextView textTitle;
    @InjectView(R.id.fraHead)
    FrameLayout fraHead;
    @InjectView(R.id.myfavorite_productlist_layout)
    LinearLayout myfavoriteProductlistLayout;
    @InjectView(R.id.bottomSpace)
    RelativeLayout bottomSpace;
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
    private TopicResponse topics;


    private TopicAdapter adapter;

    @Override
    protected void initData() {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("page", "1");
        params.put("pageNum", "8");
        showProgressDialog();//弹出进度对话框
        HttpLoader.get(ConstantsRedBaby.URL_TOPIC, params, TopicResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPIC, TopicActivity.this);

    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initContentView() {
        return R.layout.my_limit_activity;
    }


    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {

        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_TOPIC:

                topics = (TopicResponse) response;
                if (adapter == null) {
                    adapter = new TopicAdapter(topics, getApplicationContext());
                    mMylimitbuyProductList.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                dismissProgressDialog();
                break;
            case 1:
                MyToast.show(this, "Request code 1");//模拟多个请求的效果
                break;

        }

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        dismissProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpLoader.cancelRequest(ConstantsRedBaby.REQUEST_CODE_TOPIC);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
    // bottombar的点击事件
    @OnClick({R.id.imgHome, R.id.imgClassify, R.id.imgSearch, R.id.imgShoppingCar, R.id.imgMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHome:// 首页
                break;
            case R.id.imgClassify:// 分类
                break;
            case R.id.imgSearch:// 搜索
                break;
            case R.id.imgShoppingCar:// 购物车
                break;
            case R.id.imgMore: //更多

                break;
        }
    }
}
