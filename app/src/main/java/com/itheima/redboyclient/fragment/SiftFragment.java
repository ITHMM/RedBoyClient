package com.itheima.redboyclient.fragment;

import android.view.View;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;

/**
 * Created by ZCM on 2016/4/4.
 */
public class SiftFragment extends BaseFragment {
    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.sift_prod_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
//        new SiftAdapter();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
