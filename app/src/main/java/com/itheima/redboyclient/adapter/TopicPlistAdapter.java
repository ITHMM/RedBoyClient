package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.NewProductResponse;
import com.itheima.redboyclient.bean.TopicPlistResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

/**
 * Created by 李正春 on 2016/4/2.
 */
public class TopicPlistAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "TopicPlistAdapter";
    private TopicPlistResponse topicPlistResponse;
    private Context context;
    /**
     * 被点击商品详细信息的javaBean
     */
    private TopicPlistResponse.ProductlistBean currentProductlistBean;

    public TopicPlistAdapter(TopicPlistResponse topicPlistResponse, Context context) {
        this.topicPlistResponse = topicPlistResponse;
        this.context = context;
    }

    @Override
    public int getCount() {
        return topicPlistResponse.getProductlist().size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.buybuybuy_items, null);
            holder.buybuybuyPicIv = (ImageView) convertView.findViewById(R.id.buybuybuyPicIv);
            holder.buybuybuyloadingIv = (ImageView) convertView.findViewById(R.id.buybuybuyloadingIv);
            holder.buybuybuynameTV = (TextView) convertView.findViewById(R.id.buybuybuy_nameTV);
            holder.buybuybuypriceTV = (TextView) convertView.findViewById(R.id.buybuybuy_priceTV);
            holder.buybuybuy_to_goodsdetail = (RelativeLayout) convertView.findViewById(R.id.buybuybuy_to_goodsdetail);
            holder.buybuybuyprice = (TextView) convertView.findViewById(R.id.buybuybuyprice);
            holder.tv_buybuybuy = (TextView) convertView.findViewById(R.id.tv_buybuybuy);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        currentProductlistBean = topicPlistResponse.getProductlist().get(position);
        //使用Volley的ImageLoader加载图片
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + currentProductlistBean.getPic(), ImageLoader.getImageListener(holder.buybuybuyPicIv, R.drawable.product_loading, R.drawable.product_loading));
        holder.buybuybuynameTV.setText(currentProductlistBean.getName());
        holder.buybuybuypriceTV.setText("商品原价 " + currentProductlistBean.getMarketprice() + " 块");
        holder.buybuybuyprice.setText(currentProductlistBean.getPrice() + "块");
        holder.buybuybuy_to_goodsdetail.setOnClickListener(this);
        holder.tv_buybuybuy.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buybuybuy_to_goodsdetail://点击事件-->跳转到商品详情界面
                //有被点击商品详细信息的javaBean的成员变量
                Log.i(TAG, "跳转商品详情" + currentProductlistBean.getName());
                break;
            case R.id.tv_buybuybuy://点击事件-->跳转到购物车
                //有被点击商品详细信息的javaBean的成员变量
                Log.i(TAG, "跳转购物车" + currentProductlistBean.getName());
                break;
        }
    }

    static class ViewHolder {
        /**
         * 商品图片
         */
        ImageView buybuybuyPicIv;
        /**
         * 商品默认加载图片
         */
        ImageView buybuybuyloadingIv;
        /**
         * 商品名称
         */
        TextView buybuybuynameTV;
        /**
         * 商品价格
         */
        TextView buybuybuypriceTV;
        /**
         * 跳转商品详情按钮
         */
        RelativeLayout buybuybuy_to_goodsdetail;
        /**
         * 会员特价
         */
        TextView buybuybuyprice;
        /**
         * 购买按钮
         */
        TextView tv_buybuybuy;
    }
}
