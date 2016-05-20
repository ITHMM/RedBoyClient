package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/3/31.
 */
public class LogisticsFragment extends BaseFragment {

    @InjectView(R.id.head_back_text)
    TextView headBackText;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.my_logistics_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.head_back_text)

    public void onClick() {
        FragmentManager fm = getFragmentManager();
       fm.popBackStack();
    }
}
