package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.PromAdapter;
import com.itheima.redboyclient.bean.HomeResponse;
import com.itheima.redboyclient.bean.PromResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xch on 2016/3/30.
 */
public class PromFragment extends BaseFragment implements HttpLoader.ResponseListener, View.OnClickListener {
    private static final String TAG = "PromFragment";
    /**
     * PromFragment的ListView
     */
    private ListView mLvProm;
    /**
     * 促销快报的标题
     */
    private TextView mTextTitle;
    /**
     * 促销快报标题栏返回按钮
     */
    private TextView mHeaderBackBtn;

    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_prom, null);
        mLvProm = (ListView) view.findViewById(R.id.promLv);
        mTextTitle = (TextView) view.findViewById(R.id.textTitle);
        mHeaderBackBtn = (TextView) view.findViewById(R.id.headerBackBtn);
        mHeaderBackBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_TOPIC, null, PromResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPIC, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // 请求成功
    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        final PromResponse promResponse = (PromResponse) response;

        mLvProm.setAdapter(new PromAdapter(promResponse,getContext(),getFragmentManager(),PromFragment.this));
    }

    // 请求失败
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack();
    }
}
