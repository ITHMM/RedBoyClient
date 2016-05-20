package com.itheima.redboyclient.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.HotProductAdapter;
import com.itheima.redboyclient.adapter.LimitBuyAdapter;
import com.itheima.redboyclient.bean.HotProductResponse;
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;


/**
 * Created by 李正春 on 2016/4/1.
 * 热门单品的fragment
 */
public class HotProductFragment extends BaseFragment implements HttpLoader.ResponseListener, View.OnClickListener {
    /**
     * 限时抢购的ListView
     */
    private ListView mHotproduct;
    /**
     * 头布局返回按钮
     */
    private TextView mHeaderBackBtn;

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_hotproduct, null);
        mHotproduct = (ListView) view.findViewById(R.id.lv_hotproduct);
        mHeaderBackBtn = (TextView) view.findViewById(R.id.headerBackBtn);
        mHeaderBackBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_HOTPRODUCT, null, HotProductResponse.class, ConstantsRedBaby.REQUEST_CODE_HOTPRODUCT, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        HotProductResponse hotProductResponse = (HotProductResponse) response;
        mHotproduct.setAdapter(new HotProductAdapter(hotProductResponse, getContext(),getFragmentManager(),HotProductFragment.this));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack();
    }
}
