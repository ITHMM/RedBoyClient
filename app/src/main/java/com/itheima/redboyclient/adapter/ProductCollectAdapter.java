package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZCM on 2016/4/4.
 */
public class ProductCollectAdapter extends BaseAdapter {

    List<Object> productCollect;
    HomeActivity mActivity;

    public ProductCollectAdapter(HomeActivity mActivity, List<Object> productCollect) {
        this.mActivity = mActivity;
        this.productCollect = productCollect;
    }


    @Override
    public int getCount() {
        /*if (productCollect == null) {
            return 0;
        }
        return productCollect.size();*/
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return productCollect.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null && convertView instanceof RelativeLayout) {
            convertView = View.inflate(mActivity, R.layout.prod_collect_item, null);
            holder = new ViewHolder(convertView);
            holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.textContent = (TextView) convertView.findViewById(R.id.textContent);
            holder.itemDescribe = (TextView) convertView.findViewById(R.id.item_describe);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.textContent.setText("营养食品");
            holder.itemDescribe.setText("奶粉");
        }
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.imgIcon)
        ImageView imgIcon;
        @InjectView(R.id.textContent)
        TextView textContent;
        @InjectView(R.id.item_describe)
        TextView itemDescribe;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
