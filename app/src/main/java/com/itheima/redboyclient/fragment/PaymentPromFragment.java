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
 * Created by xiaoyan on 2016/4/5.
 */
public class PaymentPromFragment extends BaseFragment {

    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.head_save_text)
    TextView headSaveText;
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
        View view = View.inflate(mActivity, R.layout.payment_prom_item, null);
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

    @OnClick({R.id.head_back_text, R.id.head_save_text, R.id.pay_money_rel, R.id.pay_pos_rel, R.id.pay_ali_rel})
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        switch (view.getId()) {
            case R.id.head_back_text: //返回
                PayCenterFragment.promValue_text.setText("");
                PayCenterFragment.prom_Hint.setText("促销信息：");
                fm.popBackStack();
                break;
            case R.id.head_save_text://添加
                PayCenterFragment.prom_Hint.setText("你已享受到以下优惠：");
                fm.popBackStack();

                break;
            case R.id.pay_money_rel://4月惊喜50元礼券
                payMoneyImg.setVisibility(View.VISIBLE);
                payPosImg.setVisibility(View.INVISIBLE);
                payAliImg.setVisibility(View.INVISIBLE);
                PayCenterFragment.promValue_text.setText("4月惊喜50元礼券");

                break;
            case R.id.pay_pos_rel://国庆节80元礼券
                payMoneyImg.setVisibility(View.INVISIBLE);
                payPosImg.setVisibility(View.VISIBLE);
                payAliImg.setVisibility(View.INVISIBLE);
                PayCenterFragment.promValue_text.setText("国庆节80元礼券");
                break;
            case R.id.pay_ali_rel://圣诞节大放送80元礼券
                payMoneyImg.setVisibility(View.INVISIBLE);
                payPosImg.setVisibility(View.INVISIBLE);
                payAliImg.setVisibility(View.VISIBLE);
                PayCenterFragment.promValue_text.setText("圣诞节大放送80元礼券");
                break;
        }
    }
}
