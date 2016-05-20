package com.itheima.redboyclient.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ly on 2016/3/31.
 */
public class PayCenterFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "PayCenterFragment";
    @InjectView(R.id.ordr_submit_top_text)
    TextView ordrSubmitTopText;
    @InjectView(R.id.ordr_submit_bottom_text)
    TextView ordrSubmitBottomText;
    @InjectView(R.id.payment_address_rel)
    RelativeLayout paymentAddressRel;
    @InjectView(R.id.head_back_text)
    TextView back_text;
    //促销信息
    @InjectView(R.id.payment_prom_rel)
    RelativeLayout paymentPromRel;
    //留言
    @InjectView(R.id.payment_remark_rel)
    RelativeLayout paymentRemarkRel;
    //支付方式
    @InjectView(R.id.payment_payType_rel)
    RelativeLayout paymentPayTypeRel;
    //送货时间
    @InjectView(R.id.payment_sendTime_rel)
    RelativeLayout paymentSendTimeRel;
    static TextView sendTimeValue_text;//送货时间
    static TextView payValue_text;//支付方式
    static TextView remarkView_text;//留言
    static TextView promValue_text;//促销信息
    static TextView username;  //收货人
    static TextView phone;       //电话
    static TextView address;       //地址
    static TextView prom_Hint;       //你已享受到以下优惠：
    private FragmentManager manager;
    /**
     * 商品总价格
     */
    private final int mSumPrice;
    private TextView payment_total_buycount_text;
    private TextView payment_total_bonus_text;
    private TextView payment_total_money_text;

    public PayCenterFragment(int sum) {
        mSumPrice = sum;
    }

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.payment_center_activity, null);
        username = (TextView) view.findViewById(R.id.payment_username_text);
        phone = (TextView) view.findViewById(R.id.payment_phone_text);
        address = (TextView) view.findViewById(R.id.payment_addressDetail_text);
        payValue_text = (TextView) view.findViewById(R.id.payment_payValue_text);
        remarkView_text = (TextView) view.findViewById(R.id.payment_remarkView_text);
        sendTimeValue_text = (TextView) view.findViewById(R.id.payment_sendTimeValue_text);
        promValue_text = (TextView) view.findViewById(R.id.payment_promValue_text);
        prom_Hint = (TextView) view.findViewById(R.id.payment_prom_Hint);
        payment_total_buycount_text = (TextView) view.findViewById(R.id.payment_total_buycount_text);
        payment_total_bonus_text = (TextView) view.findViewById(R.id.payment_total_bonus_text);
        payment_total_money_text = (TextView) view.findViewById(R.id.payment_total_money_text);
        ButterKnife.inject(this, view);
        initView();
        payment_total_buycount_text.setText(String.valueOf(mSumPrice / 800) + "个");
        payment_total_bonus_text.setText(String.valueOf(mSumPrice) + "积分");
        payment_total_money_text.setText(String.valueOf(mSumPrice) + "元");

        return view;
    }


    private void initView() {
        ordrSubmitTopText.setOnClickListener(this);
        ordrSubmitBottomText.setOnClickListener(this);
        paymentAddressRel.setOnClickListener(this);
        paymentRemarkRel.setOnClickListener(this);
        paymentPayTypeRel.setOnClickListener(this);
        paymentSendTimeRel.setOnClickListener(this);
        back_text.setOnClickListener(this);
        paymentPromRel.setOnClickListener(this);
    }

    @Override
    public void initData() {
        manager = getActivity().getSupportFragmentManager();
    }

    @Override
    public boolean onBackPressed() {
        // CancelOrderFragment dialog = new CancelOrderFragment();
        //dialog.show(manager,"CancelOrderFragment");
        return false;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("onAttach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            // username.setText("收货人："+addresslistBean.getName());
            //  phone.setText("电话："+addresslistBean.getPhonenumber());
            //  address.setText("地址："+addresslistBean.getAreadetail());
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = manager.beginTransaction();
        RelativeLayout rl = (RelativeLayout) getActivity().findViewById(R.id.rl_home_fragment);
        switch (v.getId()) {
            case R.id.head_back_text://返回按钮
                CancelOrderFragment dialog = new CancelOrderFragment();
                dialog.show(manager, "CancelOrderFragment");

                break;

            case R.id.ordr_submit_top_text://右上角提交订单，此时取消所有fragment回退栈
                if(username.getText().length()<4){
                    Toast.makeText(mActivity, "请选择收货人信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(payValue_text.getText())){
                    Toast.makeText(mActivity,"请选择支付方式",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sendTimeValue_text.getText())){
                    Toast.makeText(mActivity,"请选择送货时间",Toast.LENGTH_SHORT).show();
                    return;
                }

                int n = manager.getBackStackEntryCount();
                for (int i = 0; i < n; i++) {
                    // FragmentManager.BackStackEntry backstatck = getFragmentManager().getBackStackEntryAt(i);
                    // System.out.println("Fragment"+backstatck.getName());
                    manager.popBackStack();
                }
                ft.replace(rl.getId(), new SettlementFragment(mSumPrice));
                ft.addToBackStack("SettlementFragment");
                ft.commit();
                break;
            case R.id.ordr_submit_bottom_text:  //最底部提交订单，此时取消所有fragment回退栈
                if(username.getText().length()<4){
                    Toast.makeText(mActivity,"请选择收货人信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(payValue_text.getText())){
                    Toast.makeText(mActivity,"请选择支付方式",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sendTimeValue_text.getText())){
                    Toast.makeText(mActivity,"请选择送货时间",Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = manager.getBackStackEntryCount();
                for (int i = 0; i < num; i++) {
                    manager.popBackStack();
                }
                ft.add(rl.getId(), new SettlementFragment(mSumPrice));
                ft.addToBackStack("SettlementFragment");
                ft.commit();
                break;
            case R.id.payment_address_rel:  //进入地址列表界面选择收货地址
                ft.add(rl.getId(), new AddresslistFrament());
                ft.addToBackStack("AddressFragment");
                ft.commit();
                break;
            case R.id.payment_payType_rel: //支付方式
                ft.add(rl.getId(), new PayTypeFragment());
                ft.addToBackStack("PayTypeFragment");
                ft.commit();
                break;
            case R.id.payment_prom_rel: //促销信息
                ft.add(rl.getId(), new PaymentPromFragment());
                ft.addToBackStack("PaymentPromFragment");
                ft.commit();
                break;
            case R.id.payment_remark_rel://留言
                ft.add(rl.getId(), new RemarkFragment());
                ft.addToBackStack("RemarkFragment");
                ft.commit();
                break;
            case R.id.payment_sendTime_rel://送货时间
                ft.add(rl.getId(), new SendTimeFragment());
                ft.addToBackStack("SendTimeFragment");
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
