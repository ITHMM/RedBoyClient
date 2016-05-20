package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.FavoriteResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 收藏 夹的适配器
 * Created by xiaoyan on 2016/3/31.
 */
public class FavoriteListAdapter extends BaseAdapter {

    public FavoriteResponse FavoriteDate;
    private Context context;
    private List<FavoriteResponse.FavoriteBean> favoriteBean;

    public FavoriteListAdapter(FavoriteResponse Date, Context context) {
        this.FavoriteDate = Date;
        this.context = context;
        favoriteBean = FavoriteDate.getProductlist();
    }


    @Override
    public int getCount() {
        System.out.println("FavoriteDate" + FavoriteDate);
        System.out.println("FavoriteDate.getFavoritelist()" + FavoriteDate.getProductlist());
        if (favoriteBean == null) {
            return 0;
        }
        return favoriteBean.size();
    }

    @Override
    public Object getItem(int position) {
        return favoriteBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.my_favorite_listitem, null);
            holder=new ViewHolder(convertView);
            holder.myfavoriteProductImg= (ImageView) convertView.findViewById(R.id.myfavorite_product_img);
            holder.myfavoriteTitleText= (TextView) convertView.findViewById(R.id.myfavorite_title_text);
            holder.myfavoritePriceText= (TextView) convertView.findViewById(R.id.myfavorite_price_text);
            holder.myfavoriteNostockText= (TextView) convertView.findViewById(R.id.myfavorite_nostock_text);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }
        //图片
//        holder.myfavorite_product_img.
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + favoriteBean.get(position).getPic(), ImageLoader.getImageListener(holder.myfavoriteProductImg, R.drawable.product_loading, R.drawable.product_loading));
        //名称
        holder.myfavoriteTitleText.setText(favoriteBean.get(position).getName());
        //市场价
        holder.myfavoritePriceText.setText("￥"+favoriteBean.get(position).getMarketprice());
        holder.myfavoritePriceText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //会员价
        holder.myfavoriteNostockText.setText("￥"+favoriteBean.get(position).getPrice());

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.myfavorite_product_img)
        ImageView myfavoriteProductImg;
        @InjectView(R.id.myfavorite_title_text)
        TextView myfavoriteTitleText;
        @InjectView(R.id.myfavorite_price_text)
        TextView myfavoritePriceText;
        @InjectView(R.id.myfavorite_nostock_text)
        TextView myfavoriteNostockText;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
