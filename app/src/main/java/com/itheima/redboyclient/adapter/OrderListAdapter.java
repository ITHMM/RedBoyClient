package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.OrderlistResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.fragment.OrderFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaoyan on 2016/3/31.
 */
public class OrderListAdapter extends BaseAdapter {

    private Context context;
    public  OrderlistResponse orderDate;
    public static List<OrderlistResponse.OrderlistBean> list  = new ArrayList<OrderlistResponse.OrderlistBean>();;

    public OrderListAdapter(OrderlistResponse orderDate, Context context) {
        this.orderDate = orderDate;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (orderDate == null || orderDate.getOrderlist() == null) {
            return 0;
        }
        return orderDate.getOrderlist().size();
    }

    @Override
    public Object getItem(int position) {
        return orderDate.getOrderlist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.my_order_listitem, null);
            holder = new ViewHolder(convertView);
            holder.textOrderIDIcon = (TextView) convertView.findViewById(R.id.textOrderIDIcon);
            holder.textPriceIcon = (TextView) convertView.findViewById(R.id.textPriceIcon);
            holder.textTimeIcon = (TextView) convertView.findViewById(R.id.textTimeIcon);
            holder.textStateIcon = (TextView) convertView.findViewById(R.id.textStateIcon);
            holder.textZhiFu = (TextView) convertView.findViewById(R.id.textZhiFu);
            holder.textCancelOrder = (TextView) convertView.findViewById(R.id.textCancelOrder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textOrderIDIcon.setText("订单编号：" + orderDate.getOrderlist().get(position).getOrderid());
        holder.textPriceIcon.setText("总价：" + orderDate.getOrderlist().get(position).getPrice() + "");
        holder.textTimeIcon.setText("订单生成时间：" + orderDate.getOrderlist().get(position).getTime());
        holder.textStateIcon.setText("状态：" + orderDate.getOrderlist().get(position).getStatus());
        holder.textZhiFu.setText("支付方式: 支付宝");
        holder.textCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderlistResponse.OrderlistBean orderlistBean = orderDate.orderlist.remove(position);
                notifyDataSetChanged();
                System.out.println("取消订单" + orderlistBean.getOrderid());
               if(list.contains(orderlistBean)){
                    return;
               }
                list.add(orderlistBean);

                //取消订单
               // cancelOrder(orderid);

            }
        });
        return convertView;
    }

    /**
     * 取消订单
     *
     * @param orderid
     */
    private void cancelOrder(String orderid) {

        final Map<String, String> params = new HashMap<String, String>();
        params.put("orderId", orderid);
        HttpLoader.post(ConstantsRedBaby.URL_ORDER_CANCEL, params, RBResponse.class, ConstantsRedBaby.REQUEST_CODE_ORDERCANCEL, new HttpLoader.ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                switch (requestCode) {
                    case ConstantsRedBaby.REQUEST_CODE_ORDERCANCEL:
                        //取消订单，刷新界面数据？
                        break;
                }
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });

    }

    static class ViewHolder {
        @InjectView(R.id.textOrderIDIcon)
        TextView textOrderIDIcon;
        @InjectView(R.id.textPriceIcon)
        TextView textPriceIcon;
        @InjectView(R.id.textTimeIcon)
        TextView textTimeIcon;
        @InjectView(R.id.textStateIcon)
        TextView textStateIcon;
        @InjectView(R.id.textZhiFu)
        TextView textZhiFu;
        @InjectView(R.id.textCancelOrder)
        TextView textCancelOrder;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
