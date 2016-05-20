package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 帮助
 */
public class HelpFragment extends BaseFragment {


    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.help_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.backTv, R.id.shop_help})
    public void onclick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.backTv:
                fragmentManager.popBackStack();
                break;
            case R.id.shop_help:

                transaction.add(R.id.rl_home_fragment, new ShopHelpFragment());
                transaction.addToBackStack("ShopHelpFragment");
                transaction.commit();
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
