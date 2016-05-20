package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/4/3.
 * 支付方式
 */
public class PayTypeFragment extends BaseFragment {
    @InjectView(R.id.head_save_text)
    TextView headSaveText;
    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.pay_money_img)
    ImageView payMoneyImg;
    @InjectView(R.id.pay_money_rel)
    RelativeLayout payMoneyRel;
    @InjectView(R.id.pay_pos_img)
    ImageView payPosImg;
    @InjectView(R.id.pay_pos_rel)
    RelativeLayout payPosRel;
    @InjectView(R.id.pay_ali_img)
    ImageView payAliImg;
    @InjectView(R.id.pay_ali_rel)
    RelativeLayout payAliRel;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.paytype_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        payMoneyImg.setVisibility(View.INVISIBLE);
        payPosImg.setVisibility(View.INVISIBLE);
        payAliImg.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick({R.id.head_back_text, R.id.pay_money_rel, R.id.pay_pos_rel, R.id.pay_ali_rel,R.id.head_save_text})
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();

        switch (view.getId()) {
            case R.id.head_back_text: //结束当前页面，返回上个页面
                PayCenterFragment.payValue_text.setText("");
                fm.popBackStack();
                break;
            case R.id.head_save_text: //结束当前页面，返回上个页面,保存信息
                fm.popBackStack();
                break;

            case R.id.pay_money_rel: //货到付款-现金支付
                payMoneyImg.setVisibility(View.VISIBLE);
                payPosImg.setVisibility(View.INVISIBLE);
                payAliImg.setVisibility(View.INVISIBLE);
                PayCenterFragment.payValue_text.setText("货到付款-现金支付");

                break;
            case R.id.pay_pos_rel: //货到付款-pos机支付
                payMoneyImg.setVisibility(View.INVISIBLE);
                payPosImg.setVisibility(View.VISIBLE);
                payAliImg.setVisibility(View.INVISIBLE);
                PayCenterFragment.payValue_text.setText("货到付款-pos机支付");
                break;
            case R.id.pay_ali_rel: //支付宝支付
                payMoneyImg.setVisibility(View.INVISIBLE);
                payPosImg.setVisibility(View.INVISIBLE);
                payAliImg.setVisibility(View.VISIBLE);
                PayCenterFragment.payValue_text.setText("支付宝支付");
                break;
        }
    }
}
