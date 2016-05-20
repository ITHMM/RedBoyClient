package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 帮助
 */
public class ShopHelpFragment extends BaseFragment {

    @InjectView(R.id.backTv)
    TextView backTv;
    @InjectView(R.id.fraHead)
    FrameLayout fraHead;



    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.shop_help, null);
        ButterKnife.inject(this, view);


        return view;
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.backTv})
    public void onclick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.backTv:
                fragmentManager.popBackStack();
                break;

        }

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
