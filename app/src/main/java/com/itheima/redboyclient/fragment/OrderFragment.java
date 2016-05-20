package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.OrderListAdapter;
import com.itheima.redboyclient.adapter.cancelOrderListAdapter;
import com.itheima.redboyclient.bean.OrderlistResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 我的订单列表
 */
public class OrderFragment extends BaseFragment implements HttpLoader.ResponseListener {

    private static final String TAG_DETAILS = "TAG_DETAILS";
    //返回
    @InjectView(R.id.head_back_text)
    TextView headBackText;
    // 一个月内订单
    @InjectView(R.id.my_order_month)
    TextView myOrderMonth;
    // 所有订单
    @InjectView(R.id.my_order_all)
    TextView myOrderAll;
    // 取消订单
    @InjectView(R.id.my_order_notsend)
    TextView myOrderNotsend;
    // 订单列表
    @InjectView(R.id.my_order_list)
    ListView myOrderList;

    @InjectView(R.id.ll_content)
    LinearLayout llContent;
    //暂无订单
    @InjectView(R.id.my_order_null_text)
    TextView myOrderNullText;
    //订单列表
    private OrderListAdapter listAdapter;
    //取消订单列表
    private cancelOrderListAdapter cancelAdapter;

    private OrderlistResponse orderlist;

    public OrderFragment() {

    }

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.my_order_activity, null);
       ButterKnife.inject(this, view);
       myOrderMonth.setSelected(true);
        return view;
    }

    /**
     * 近一个月订单数据
     */
    @Override
    public void initData() {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("type", "3");
        params.put("page", "1");
        params.put("pageNum", "10");
        HttpLoader.get(ConstantsRedBaby.URL_ORDER_LIST, params, OrderlistResponse.class, ConstantsRedBaby.REQUEST_CODE_ORDERLIST, this, false);

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

    @OnClick({R.id.head_back_text, R.id.my_order_month, R.id.my_order_all, R.id.my_order_notsend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_back_text: //返回更多界面
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.rl_home_fragment, new MoreFragment());
//                transaction.commit();

                break;
            case R.id.my_order_month://一个月内订单
                myOrderAll.setSelected(false);
                myOrderMonth.setSelected(true);
                myOrderNotsend.setSelected(false);
                myOrderList.setVisibility(View.VISIBLE);
                myOrderNullText.setVisibility(View.GONE);
                myOrderList.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

                break;
            case R.id.my_order_all://一个月前订单
                myOrderAll.setSelected(true);
                myOrderMonth.setSelected(false);
                myOrderNotsend.setSelected(false);
                myOrderList.setVisibility(View.INVISIBLE);
                myOrderNullText.setVisibility(View.VISIBLE);
                break;
            case R.id.my_order_notsend://取消订单界面
                myOrderAll.setSelected(false);
                myOrderMonth.setSelected(false);
                myOrderNotsend.setSelected(true);
                myOrderList.setVisibility(View.VISIBLE);
                myOrderNullText.setVisibility(View.GONE);
                cancelAdapter = new cancelOrderListAdapter(mActivity, OrderListAdapter.list);
                myOrderList.setAdapter(cancelAdapter);
                cancelAdapter.notifyDataSetChanged();

                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_ORDERLIST:

                orderlist = (OrderlistResponse) response;
                if (listAdapter == null) {
                    listAdapter = new OrderListAdapter(orderlist, mActivity);
                    myOrderList.setAdapter(listAdapter);
                    /**
                     * 订单条目点击事件
                     */
                    myOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.add(R.id.rl_home_fragment, new OrderDetailsFragment(), TAG_DETAILS);
                            transaction.addToBackStack(TAG_DETAILS);
                            transaction.commit();
                        }
                    });
                } else {
                    listAdapter.notifyDataSetChanged();
                }

                break;
        }

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
    }
}
