package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.OrderlistResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaoyan on 2016/4/1.
 * 取消订单列表
 */
public class cancelOrderListAdapter extends BaseAdapter {
    private Context context;
    public List<OrderlistResponse.OrderlistBean> cancelList;

    public cancelOrderListAdapter(Context context, List<OrderlistResponse.OrderlistBean> cancelList) {
        this.context = context;
        this.cancelList = cancelList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.my_cancel_order, null);
            holder = new ViewHolder(convertView);
            holder.textOrderIDIcon = (TextView) convertView.findViewById(R.id.textOrderIDIcon);
            holder.textPriceIcon = (TextView) convertView.findViewById(R.id.textPriceIcon);
            holder.textTimeIcon = (TextView) convertView.findViewById(R.id.textTimeIcon);
            holder.textStateIcon = (TextView) convertView.findViewById(R.id.textStateIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textOrderIDIcon.setText("订单编号：" + cancelList.get(position).getOrderid());
        holder.textPriceIcon.setText("总价：" + cancelList.get(position).getPrice() + "");
        holder.textTimeIcon.setText("订单生成时间：" + cancelList.get(position).getTime());
        holder.textStateIcon.setText("状态：" + cancelList.get(position).getStatus());

        return convertView;
    }

    @Override
    public int getCount() {
        return cancelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
