package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.os.Handler;
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
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.fragment.LimitBuyFragment;
import com.itheima.redboyclient.fragment.ProductDetailFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.dao.DbUtils;

import org.seny.android.utils.MyToast;

/**
 * Created by 李正春 on 2016/4/2.
 */
public class LimitBuyAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "LimitBuyAdapter";
    private LimitBuyResponse limitBuyResponse;
    private Context context;
    /**
     * 通过构造方法传入的跳转Fragment需要用到的FragmentManager
     */
    private FragmentManager mFragmentManager;
    /**
     * 在Adapter中隐藏父Fragment需要通过构造方法传进来
     */
    private LimitBuyFragment mLimitBuyFragment;
    private ViewHolder holder;

    public LimitBuyAdapter(LimitBuyResponse limitBuyResponse, Context context, FragmentManager fragmentManager, LimitBuyFragment limitBuyFragment) {
        this.limitBuyResponse = limitBuyResponse;
        this.context = context;
        this.mFragmentManager = fragmentManager;
        this.mLimitBuyFragment = limitBuyFragment;
        handler.sendEmptyMessage(1);
    }

    @Override
    public int getCount() {
        return limitBuyResponse.getProductlist().size();
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
        holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.limitbuy_items, null);
            holder.limitbuyPicIv = (ImageView) convertView.findViewById(R.id.limitbuyPicIv);
            holder.limitbuyloadingIv = (ImageView) convertView.findViewById(R.id.limitbuyloadingIv);
            holder.limitbuy_nameTV = (TextView) convertView.findViewById(R.id.limitbuy_nameTV);
            holder.limitbuy_priceTV = (TextView) convertView.findViewById(R.id.limitbuy_priceTV);
            holder.limitbuy_to_goodsdetail = (LinearLayout) convertView.findViewById(R.id.limitbuy_to_goodsdetail);
            holder.limitpriceTV = (TextView) convertView.findViewById(R.id.limitpriceTV);
            holder.limitlefttimeTV = (TextView) convertView.findViewById(R.id.limitlefttimeTV);
            holder.tv_limitbuy = (TextView) convertView.findViewById(R.id.tv_limitbuy);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        LimitBuyResponse.ProductlistBean productlistBean = limitBuyResponse.getProductlist().get(position);
        //使用Volley的ImageLoader加载图片
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + productlistBean.getPic(), ImageLoader.getImageListener(holder.limitbuyPicIv, R.drawable.product_loading, R.drawable.product_loading));
        holder.limitbuy_nameTV.setText(productlistBean.getName());
        holder.limitbuy_priceTV.setText("商品原价 " + productlistBean.getPrice() + " 块");
        long mss = productlistBean.getLefttime() / 1000;//服务器时间不对,多除一千
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
//        new CountDownTimer(30000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                holder.limitlefttimeTV.setText(": " + millisUntilFinished / 1000);
//            }
//            public void onFinish() {
//                holder.limitlefttimeTV.setText("done!");
//            }
//        }.start();
        holder.limitlefttimeTV.setText(days + "天" + hours + ":" + minutes + ":" + seconds);
        holder.limitpriceTV.setText(productlistBean.getLimitprice() + "块");
        holder.tv_limitbuy.setTag(position);
        holder.limitbuy_to_goodsdetail.setTag(position);
        holder.limitbuy_to_goodsdetail.setOnClickListener(this);
        holder.tv_limitbuy.setOnClickListener(this);
        return convertView;
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    boolean isNeedCountTime = false;
                    //①：其实在这块需要精确计算当前时间
                    for (int index = 0; index < limitBuyResponse.getProductlist().size(); index++) {
                        LimitBuyResponse.ProductlistBean productlistBean = limitBuyResponse.getProductlist().get(index);
                        long time = productlistBean.getLefttime();
                        if (time > 1000 * 1000) {//判断是否还有条目能够倒计时，如果能够倒计时的话，延迟一秒，让它接着倒计时
                            isNeedCountTime = true;
                            productlistBean.setLefttime(time - 1000 * 1000);
                        } else {
                            productlistBean.setLefttime(0);
                        }
                    }
                    //②：for循环执行的时间
                    notifyDataSetChanged();
                    if (isNeedCountTime) {

                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }

        }

    };

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        int position = Integer.parseInt(v.getTag().toString());
        LimitBuyResponse.ProductlistBean currentProductListBean = limitBuyResponse.getProductlist().get(position);
        switch (v.getId()) {
            case R.id.limitbuy_to_goodsdetail://点击事件-->跳转到商品详情界面
                //有被点击商品详细信息的javaBean的成员变量
                Log.i(TAG, "跳转商品详情" + currentProductListBean.getName() + "-------" + position);
                ft.add(R.id.rl_home_fragment, new ProductDetailFragment(currentProductListBean.getId()));
                ft.addToBackStack("HomeFragment");
                ft.hide(mLimitBuyFragment);
                ft.commit();
                break;
            case R.id.tv_limitbuy://点击事件-->跳转到购物车
                //有被点击商品详细信息的javaBean的成员变量
                boolean loginState = new SpHelp().getsp("login", false);//判断是否已经登录
                if (!loginState) {
                    MyToast.show(context,"还没有登录,请先登录");
                    break;
                }
                Log.i(TAG, "加入购物车" + currentProductListBean.getName() + "-------" + position);
                //将商品信息添加到数据库
                DbUtils dbUtils = new DbUtils(context);
                dbUtils.insert(currentProductListBean.getName(), String.valueOf(currentProductListBean.getId()), "1", "", "");
                MyToast.show(context,currentProductListBean.getName() + "已添加入购物车中");

//                int[] start_location = new int[2];
//                holder.limitbuyPicIv.getLocationInWindow(start_location);//获取点击商品图片的位置
//                Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f);
//                animation.setDuration(2000);
//                animation.setFillAfter(false);
//                holder.limitbuyPicIv.startAnimation(animation);

//                if(mHolderClickListener!=null){
//                    int[] start_location = new int[2];
//                    holder.limitbuyPicIv.getLocationInWindow(start_location);//获取点击商品图片的位置
//                    Drawable drawable = holder.limitbuyPicIv.getDrawable();//复制一个新的商品图标
//                    mHolderClickListener.onHolderClick(drawable,start_location);
//                }
                break;
        }
    }
//    public void SetOnSetHolderClickListener(HolderClickListener holderClickListener){
//        this.mHolderClickListener = holderClickListener;
//    }
//    public interface HolderClickListener{
//        public void onHolderClick(Drawable drawable,int[] start_location);
//    }
//    private LayoutInflater layoutInflater;
//    private HolderClickListener mHolderClickListener;


    static class ViewHolder {
        /**
         * 商品图片
         */
        ImageView limitbuyPicIv;
        /**
         * 商品默认加载图片
         */
        ImageView limitbuyloadingIv;
        /**
         * 商品名称
         */
        TextView limitbuy_nameTV;
        /**
         * 商品价格
         */
        TextView limitbuy_priceTV;
        /**
         * 跳转商品详情按钮
         */
        LinearLayout limitbuy_to_goodsdetail;
        /**
         * 限时特价
         */
        TextView limitpriceTV;
        /**
         * 剩余时间
         */
        TextView limitlefttimeTV;
        /**
         * 抢购按钮
         */
        TextView tv_limitbuy;
    }
}
