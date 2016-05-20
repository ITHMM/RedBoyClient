package com.itheima.redboyclient.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.LimitBuyAdapter;
import com.itheima.redboyclient.adapter.NewProductAdapter;
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.bean.NewProductResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;


/**
 * Created by 李正春 on 2016/4/1.
 * 新品上架的fragment
 */
public class NewProductFragment extends BaseFragment implements HttpLoader.ResponseListener, View.OnClickListener {
    /**
     * 限时抢购的ListView
     */
    private ListView lv_newproduct;
    /**
     * 头布局返回按钮
     */
    private TextView mHeaderBackBtn;

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_newproduct, null);
        lv_newproduct = (ListView) view.findViewById(R.id.lv_newproduct);
        mHeaderBackBtn = (TextView) view.findViewById(R.id.headerBackBtn);
        mHeaderBackBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_NEWPRODUCT, null, NewProductResponse.class, ConstantsRedBaby.REQUEST_CODE_NEWPRODUCT, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        NewProductResponse newProductResponse = (NewProductResponse) response;
        lv_newproduct.setAdapter(new NewProductAdapter(newProductResponse, getContext(),getFragmentManager(),NewProductFragment.this));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack();
    }
}
