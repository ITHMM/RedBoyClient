package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.ProductListResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZCM on 2016/4/5.
 */
public class ProductListAdapter extends BaseAdapter {
    List<ProductListResponse.ProductlistBean> productlist;

    public ProductListAdapter(List<ProductListResponse.ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    @Override
    public int getCount() {
        return productlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(App.application, R.layout.item_search_result, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProductListResponse.ProductlistBean productlistBean = productlist.get(position);
        ALog.i(productlistBean.getPic());
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + productlistBean.getPic(), ImageLoader.getImageListener(viewHolder.searchIcon, R.drawable.product_loading, R.drawable.product_loading));
        viewHolder.searchTitle.setText(productlistBean.getName());
        viewHolder.searchPrice.setText("￥" + productlistBean.getPrice());
        viewHolder.searchMarketprice.setText("￥" + productlistBean.getMarketprice());
        viewHolder.searchCommentCount.setText("已有" + productlistBean.getComment_count() + "人评价");
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.search_icon)
        ImageView searchIcon;
        @InjectView(R.id.search_title)
        TextView searchTitle;
        @InjectView(R.id.search_price)
        TextView searchPrice;
        @InjectView(R.id.search_marketprice)
        TextView searchMarketprice;
        @InjectView(R.id.rl_search_marketprice)
        RelativeLayout rlSearchMarketprice;
        @InjectView(R.id.search_comment_count)
        TextView searchCommentCount;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
