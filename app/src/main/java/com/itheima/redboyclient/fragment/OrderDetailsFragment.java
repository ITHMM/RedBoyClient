package com.itheima.redboyclient.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/3/31.
 */
public class OrderDetailsFragment extends BaseFragment {


    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.ordr_logistics_rel)
    RelativeLayout ordrLogisticsRel;

    private FragmentManager fm;
    private FragmentTransaction transaction;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.my_order_detail_activity, null);
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

    @OnClick({R.id.head_back_text, R.id.ordr_logistics_rel})
    public void onClick(View view) {
        fm = getFragmentManager();
        transaction = fm .beginTransaction();
        switch (view.getId()) {
            case R.id.head_back_text:
                //结束当前Fragment任务栈，返回上个Fragment任务栈
                fm.popBackStack();
                break;
            case R.id.ordr_logistics_rel: //物流
                transaction.add(R.id.rl_home_fragment,new LogisticsFragment());
                transaction.addToBackStack("LogisticsFragment");
                transaction.commit();
                break;
        }
    }
}
