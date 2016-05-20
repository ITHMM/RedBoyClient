package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.SearchResultResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xch on 2016/4/2.
 * 搜索结果列表的adapter
 */
public class SearchResultAdapter extends BaseAdapter {
    private List<SearchResultResponse.ProductlistBean> productlist;

    public SearchResultAdapter(List<SearchResultResponse.ProductlistBean> productlist) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(App.application, R.layout.item_search_result, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SearchResultResponse.ProductlistBean productlistBean = productlist.get(position);
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + productlistBean.getPic(), ImageLoader.getImageListener(holder.searchIcon, R.drawable.product_loading, R.drawable.product_loading));
        holder.searchTitle.setText(productlistBean.getName());
        holder.searchPrice.setText("￥" + productlistBean.getPrice());
        holder.searchMarketprice.setText("￥" + productlistBean.getMarketprice());
        holder.searchCommentCount.setText("已有"+productlistBean.getComment_count()+"人评价");
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
        @InjectView(R.id.search_comment_count)
        TextView searchCommentCount;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
