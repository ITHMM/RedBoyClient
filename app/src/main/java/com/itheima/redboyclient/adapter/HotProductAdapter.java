package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.DB.SpHelp;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.HotProductResponse;
import com.itheima.redboyclient.fragment.HotProductFragment;
import com.itheima.redboyclient.fragment.ProductDetailFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.dao.DbUtils;

import org.seny.android.utils.MyToast;

/**
 * Created by 李正春 on 2016/4/2.
 */
public class HotProductAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "HotProductAdapter";
    private HotProductResponse hotProductResponse;
    private Context context;
    /**
     * 通过构造方法传入的跳转Fragment需要用到的FragmentManager
     */
    private FragmentManager mFragmentManager;
    /**
     * 在Adapter中隐藏父Fragment需要通过构造方法传进来
     */
    private HotProductFragment mHotProductFragment;

    public HotProductAdapter(HotProductResponse hotProductResponse, Context context, FragmentManager fragmentManager, HotProductFragment hotProductFragment) {
        this.hotProductResponse = hotProductResponse;
        this.context = context;
        this.mFragmentManager = fragmentManager;
        this.mHotProductFragment = hotProductFragment;
    }

    @Override
    public int getCount() {
        return hotProductResponse.getProductlist().size();
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
            holder.buybuybuy_to_goodsdetail = (LinearLayout) convertView.findViewById(R.id.buybuybuy_to_goodsdetail);
            holder.buybuybuyprice = (TextView) convertView.findViewById(R.id.buybuybuyprice);
            holder.tv_buybuybuy = (TextView) convertView.findViewById(R.id.tv_buybuybuy);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        HotProductResponse.ProductlistBean productListBean = hotProductResponse.getProductlist().get(position);
        //使用Volley的ImageLoader加载图片
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + productListBean.getPic(), ImageLoader.getImageListener(holder.buybuybuyPicIv, R.drawable.product_loading, R.drawable.product_loading));
        holder.buybuybuynameTV.setText(productListBean.getName());
        holder.buybuybuypriceTV.setText("商品原价 " + productListBean.getMarketprice() + " 块");
        holder.buybuybuyprice.setText(productListBean.getPrice() + "块");
        holder.buybuybuy_to_goodsdetail.setTag(position);
        holder.tv_buybuybuy.setTag(position);
        holder.buybuybuy_to_goodsdetail.setOnClickListener(this);
        holder.tv_buybuybuy.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        int position = Integer.parseInt(v.getTag().toString());
        HotProductResponse.ProductlistBean currentProductListBean = hotProductResponse.getProductlist().get(position);
        switch (v.getId()) {
            case R.id.buybuybuy_to_goodsdetail://点击事件-->跳转到商品详情界面
                //有被点击商品详细信息的javaBean的成员变量
                Log.i(TAG, "跳转商品详情" + currentProductListBean.getName());
                ft.add(R.id.rl_home_fragment, new ProductDetailFragment(currentProductListBean.getId()));
                ft.addToBackStack("HomeFragment");
                ft.hide(mHotProductFragment);
                ft.commit();
                break;
            case R.id.tv_buybuybuy://点击事件-->跳转到购物车
                //有被点击商品详细信息的javaBean的成员变量
                boolean loginState = new SpHelp().getsp("login", false);//判断是否已经登录
                if (!loginState) {
                    MyToast.show(context,"还没有登录,请先登录");
                    break;
                }
                Log.i(TAG, "加入购物车" + currentProductListBean.getName());
//         //将商品信息添加到数据库
                DbUtils dbUtils = new DbUtils(context);
                dbUtils.insert(currentProductListBean.getName(), String.valueOf(currentProductListBean.getId()), "1", "", "");
                MyToast.show(context, currentProductListBean.getName() + "已添加入购物车中");
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
        LinearLayout buybuybuy_to_goodsdetail;
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
