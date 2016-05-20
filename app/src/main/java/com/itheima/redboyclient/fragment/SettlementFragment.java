package com.itheima.redboyclient.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ly on 2016/3/31.
 */
public class SettlementFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.fraHead)
    FrameLayout fraHead;
    @InjectView(R.id.orderid_text)
    TextView orderidText;
    @InjectView(R.id.orderid_value_text)
    TextView orderidValueText;
    @InjectView(R.id.paymoney_text)
    TextView paymoneyText;
    @InjectView(R.id.paymoney_value_text)
    TextView paymoneyValueText;
    @InjectView(R.id.paytype_text)
    TextView paytypeText;
    @InjectView(R.id.paytype_value_text)
    TextView paytypeValueText;
    @InjectView(R.id.continue_shoping_text)
    TextView continueShopingText;
    @InjectView(R.id.to_ordr_detail_text)
    TextView toOrdrDetailText;
    @InjectView(R.id.textOrderTip)
    TextView textOrderTip;
    @InjectView(R.id.textOrderTipNum)
    TextView textOrderTipNum;
    @InjectView(R.id.scrLayout)
    ScrollView scrLayout;
    /**
     * 商品总价
     */
    private final int mSumPrice;

    public SettlementFragment(int sumPrice) {
        mSumPrice = sumPrice;
    }

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.ordr_submit_ok_activity, null);
        ButterKnife.inject(this, view);
        paymoneyValueText.setText(mSumPrice + "块");
        initView();
        return view;
    }

    private void initView() {
        continueShopingText.setOnClickListener(this);
        toOrdrDetailText.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        RelativeLayout rl = (RelativeLayout) getActivity().findViewById(R.id.rl_home_fragment);
        switch (v.getId()) {
            case R.id.continue_shoping_text:
                ft.replace(rl.getId(), new HomeFragment());
                ft.commit();
                break;
            case R.id.to_ordr_detail_text:  //结束回退栈，进入订单列表
                manager.popBackStack();
                ft.add(rl.getId(), new OrderFragment());
                ft.addToBackStack("OrderFragment");
                ft.commit();

                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
