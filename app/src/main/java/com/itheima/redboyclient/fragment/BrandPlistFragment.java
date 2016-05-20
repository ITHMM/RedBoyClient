package com.itheima.redboyclient.fragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BrandPlistAdapter;
import com.itheima.redboyclient.adapter.LimitBuyAdapter;
import com.itheima.redboyclient.bean.BrandPlistResponse;
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;


/**
 * Created by 李正春 on 2016/4/1.
 * 品牌商品列表的fragment
 */
public class BrandPlistFragment extends BaseFragment implements HttpLoader.ResponseListener {
    /**
     * 限时抢购的ListView
     */
    private ListView mLvBrandplist;

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_brandplist, null);
        mLvBrandplist = (ListView) view.findViewById(R.id.lv_brandplist);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_BRANDPLIST, null, BrandPlistResponse.class, ConstantsRedBaby.REQUEST_CODE_BRANDPLIST, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        BrandPlistResponse brandPlistResponse = (BrandPlistResponse) response;
        mLvBrandplist.setAdapter(new BrandPlistAdapter(brandPlistResponse, getContext()));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
