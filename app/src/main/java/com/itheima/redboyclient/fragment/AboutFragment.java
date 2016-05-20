package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.UserInfoResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/3/31/031.
 */
public class AboutFragment extends BaseFragment {

    @InjectView(R.id.backTv)
    TextView backTv;

    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.about_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.backTv})
    public void OnClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.backTv://返回  更多
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
