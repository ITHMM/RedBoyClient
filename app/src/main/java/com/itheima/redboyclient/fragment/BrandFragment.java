package com.itheima.redboyclient.fragment;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BrandAdapter;
import com.itheima.redboyclient.bean.BrandResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;


/**
 * Created by 李正春 on 2016/4/1.
 * 推荐品牌的fragment
 */
public class BrandFragment extends BaseFragment implements HttpLoader.ResponseListener, View.OnClickListener {
    private static final String TAG = "BrandFragment";
    /**
     * 推荐品牌标题栏的线性布局
     */
    private LinearLayout mLl_brand;
    /**
     * 推荐品牌标题栏的标题
     */
    private TextView mTvHeadBrand;
    /**
     * 推荐品牌标题栏的返回按钮
     */
    private TextView mTvHeadTitleBack;
    /**
     * 推荐品牌详情的ListView
     */
    private ListView mLvBrand;

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_brand, null);
        mLl_brand = (LinearLayout) view.findViewById(R.id.ll_brand);
        mTvHeadBrand = (TextView) view.findViewById(R.id.tv_head_brand);
        mTvHeadTitleBack = (TextView) view.findViewById(R.id.tv_head_title_back);
        mLvBrand = (ListView) view.findViewById(R.id.lvBrand);
        mTvHeadTitleBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_BRAND, null, BrandResponse.class, ConstantsRedBaby.REQUEST_CODE_BRAND, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        Log.i(TAG, "onGetResponseSuccess: ------------------" + response);
        BrandResponse brandResponse = (BrandResponse) response;
        mLvBrand.setAdapter(new BrandAdapter(brandResponse, getContext(),getFragmentManager(),BrandFragment.this));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack();
    }
}
