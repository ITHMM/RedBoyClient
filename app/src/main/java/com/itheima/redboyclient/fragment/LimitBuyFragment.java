package com.itheima.redboyclient.fragment;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BrandAdapter;
import com.itheima.redboyclient.adapter.LimitBuyAdapter;
import com.itheima.redboyclient.bean.BrandResponse;
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;


/**
 * Created by 李正春 on 2016/4/1.
 * 限时抢购的fragment
 */
public class LimitBuyFragment extends BaseFragment implements HttpLoader.ResponseListener, View.OnClickListener {
    /**
     * 限时抢购的ListView
     */
    private ListView mLvLimitbuy;
    /**
     * 头布局返回按钮
     */
    private TextView mHeaderBackBtn;

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_limitbuy, null);
        mLvLimitbuy = (ListView) view.findViewById(R.id.lv_limitbuy);
        mHeaderBackBtn = (TextView) view.findViewById(R.id.headerBackBtn);
        mHeaderBackBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_LIMITBUY, null, LimitBuyResponse.class, ConstantsRedBaby.REQUEST_CODE_LIMITBUY, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        LimitBuyResponse limitBuyResponse = (LimitBuyResponse) response;
        mLvLimitbuy.setAdapter(new LimitBuyAdapter(limitBuyResponse, getContext(),getFragmentManager(),LimitBuyFragment.this));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack();
    }
}
