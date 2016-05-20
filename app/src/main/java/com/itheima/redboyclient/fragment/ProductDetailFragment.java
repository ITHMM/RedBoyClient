package com.itheima.redboyclient.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.DB.SpHelp;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.ProductDetailAdapter;
import com.itheima.redboyclient.bean.ProductDetailResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.dao.DbUtils;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;
import org.seny.android.utils.MyToast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class ProductDetailFragment extends BaseFragment implements HttpLoader.ResponseListener,
    View.OnClickListener {

    private static final String TAG = "ProductDetailFragment";
    /**
     * 标题栏放回按钮
     */
    private TextView titleBack;
    private TextView title;
    private RelativeLayout relTop;
    /**
     * ViewPager指针
     */
    private PagerTabStrip imgPoint;
    /**
     * ViewPager
     */
    private ViewPager productViewPager;
    private TextView textProductName;
    /**
     * 商品名称
     */
    private TextView textProductNameValue;
    private TextView textProductId;
    /**
     * 商品Id
     */
    private TextView textProductIdValue;
    private TextView textOriginalPrice;
    /**
     * 市场价
     */
    private TextView textOriginalPriceValue;
    private TextView textProdGrade;
    /**
     * 商品评分
     */
    private ImageView textProdGradeValue;
    private RelativeLayout priceLayout;
    private TextView textPrice;
    /**
     * 售价
     */
    private TextView textPriceValue;
    private TextView textProdNum;
    /**
     * 购买数量编辑
     */
    private EditText prodNumValue;
    private TextView textColor;
    private TextView textColorValue;
    private TextView textSize;
    private TextView textSizeValue;
    private RelativeLayout prod_property;
    /**
     * 加入购物车点击
     */
    private TextView textPutIntoShopcar;
    /**
     * 收藏点击
     */
    private TextView textProdToCollect;
    private ImageView imgServiceImg;
    /**
     * 商品描述点击
     */
    private RelativeLayout relDescription;
    private TextView textLookProdStock;
    /**
     * 是否有货(有货)或者(无货)
     */
    private TextView textProdIsStock;
    /**
     * 查看存货点击
     */
    private RelativeLayout relProdStock;
    private TextView textProductComment;
    /**
     * 购买评论条数
     */
    private TextView textProductCommentNum;
    /**
     * 购买评论,点击
     */
    private RelativeLayout relProductComment;
    /**
     * 订购电话
     */
    private TextView orderTelTv;
    private LinearLayout productInfo;
    private TextView textProductInfoIsNull;
    /**
     * 被点击商品的Id
     */
    private int mCurrentId;
    /**
     * 商品详情的javaBean
     */
    private ProductDetailResponse productDetailResponse;

    public ProductDetailFragment(int id) {
        this.mCurrentId = id;
    }

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_product_detail, null);
        titleBack = (TextView) view.findViewById(R.id.titleBack);
        title = (TextView) view.findViewById(R.id.title);
        relTop = (RelativeLayout) view.findViewById(R.id.relTop);
        imgPoint = (PagerTabStrip) view.findViewById(R.id.imgPoint);
        productViewPager = (ViewPager) view.findViewById(R.id.productViewPager);
        textProductName = (TextView) view.findViewById(R.id.textProductName);
        textProductNameValue = (TextView) view.findViewById(R.id.textProductNameValue);
        textProductId = (TextView) view.findViewById(R.id.textProductId);
        textProductIdValue = (TextView) view.findViewById(R.id.textProductIdValue);
        textOriginalPrice = (TextView) view.findViewById(R.id.textOriginalPrice);
        textOriginalPriceValue = (TextView) view.findViewById(R.id.textOriginalPriceValue);
        textProdGrade = (TextView) view.findViewById(R.id.textProdGrade);
        textProdGradeValue = (ImageView) view.findViewById(R.id.textProdGradeValue);
        priceLayout = (RelativeLayout) view.findViewById(R.id.priceLayout);
        textPrice = (TextView) view.findViewById(R.id.textPrice);
        textPriceValue = (TextView) view.findViewById(R.id.textPriceValue);
        textProdNum = (TextView) view.findViewById(R.id.textProdNum);
        prodNumValue = (EditText) view.findViewById(R.id.prodNumValue);
        textColor = (TextView) view.findViewById(R.id.textColor);
        textColorValue = (TextView) view.findViewById(R.id.textColorValue);
        textSize = (TextView) view.findViewById(R.id.textSize);
        textSizeValue = (TextView) view.findViewById(R.id.textSizeValue);
        prod_property = (RelativeLayout) view.findViewById(R.id.prod_property);
        textPutIntoShopcar = (TextView) view.findViewById(R.id.textPutIntoShopcar);
        textProdToCollect = (TextView) view.findViewById(R.id.textProdToCollect);
        imgServiceImg = (ImageView) view.findViewById(R.id.imgServiceImg);
        relDescription = (RelativeLayout) view.findViewById(R.id.relDescription);
        textLookProdStock = (TextView) view.findViewById(R.id.textLookProdStock);
        textProdIsStock = (TextView) view.findViewById(R.id.textProdIsStock);
        relProdStock = (RelativeLayout) view.findViewById(R.id.relProdStock);
        textProductComment = (TextView) view.findViewById(R.id.textProductComment);
        textProductCommentNum = (TextView) view.findViewById(R.id.textProductCommentNum);
        relProductComment = (RelativeLayout) view.findViewById(R.id.relProductComment);
        orderTelTv = (TextView) view.findViewById(R.id.orderTelTv);
        productInfo = (LinearLayout) view.findViewById(R.id.productInfo);
        textProductInfoIsNull = (TextView) view.findViewById(R.id.textProductInfoIsNull);

        textPutIntoShopcar.setOnClickListener(this);
        textProdToCollect.setOnClickListener(this);
        relDescription.setOnClickListener(this);
        relProdStock.setOnClickListener(this);
        relProductComment.setOnClickListener(this);
        orderTelTv.setOnClickListener(this);
        titleBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Pid", mCurrentId + "");
        HttpLoader.get(ConstantsRedBaby.URL_PRODUCT_DETAIL, params, ProductDetailResponse.class,
            ConstantsRedBaby.REQUEST_CODE_PRODUCT_DETAIL, this, true);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {//请求成功
        productDetailResponse = (ProductDetailResponse) response;
        String name = productDetailResponse.getProduct().getName();
        String id = productDetailResponse.getProduct().getId() + "";
        String marketprice = productDetailResponse.getProduct().getMarketprice() + "";
        String price = productDetailResponse.getProduct().getPrice() + "";
        String available = productDetailResponse.getProduct().getAvailable();
        String comment_count = productDetailResponse.getProduct().getComment_count() + "";
        textProductNameValue.setText(name);
        textProductIdValue.setText(id);
        textOriginalPriceValue.setText(marketprice);
//        textProdGradeValue.setImageResource(R.drawable.about);
        textPriceValue.setText(price);
        textProdIsStock.setText(available);
        textProdIsStock.setTextColor(Color.GREEN);
        textProductCommentNum.setText(comment_count);
        productViewPager.setAdapter(new ProductDetailAdapter(productDetailResponse));
        carousel();
    }
    private Handler handler;
    private int oldState;
    /**
     * 轮播viewpager的图片
     */
    private void carousel() {
        if(handler == null){
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(productViewPager != null){
                        int currentItem = productViewPager.getCurrentItem();
                        ++currentItem;
                        if (currentItem > productViewPager.getChildCount()){
                            currentItem =0;
                        }
                        productViewPager.setCurrentItem(currentItem);
                        handler.sendEmptyMessageDelayed(0,2000);
                    }
                }
            };
            handler.sendEmptyMessageDelayed(0,2000);
            // viewpager的滑动变化监听
            productViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    ALog.i("stat:" + state);
                    // 1代表手指触摸上去,2代表手指离开
                    if(state == 1){
                        handler.removeCallbacksAndMessages(null);
                    }else if(state == 2 && oldState == 1){
                        handler.sendEmptyMessageDelayed(0,3000);
                    }
                    oldState = state;
                }
            });
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {//请求失败
        MyToast.show(getContext(), requestCode + "商品详情链接请求失败");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textPutIntoShopcar://加入购物车
                String promNum0 = prodNumValue.getEditableText().toString().trim();
                if (TextUtils.isEmpty(promNum0)) {
                    MyToast.show(getContext(), "购买数量不能为空");
                    break;
                }
                try {
                    int promNum = Integer.parseInt(promNum0);
                } catch (Exception e) {
                    MyToast.show(getContext(), "购买数量必须为数字");
                }
                boolean loginState = new SpHelp().getsp("login", false);//判断是否已经登录
                if (!loginState) {
                    MyToast.show(getContext(), "还没有登录,请先登录");
                    break;
                }
                Log.i(TAG, "加入购物车" + productDetailResponse.getProduct().getName());
                //将商品信息添加到数据库
                DbUtils dbUtils = new DbUtils(getContext());
                dbUtils.insert(productDetailResponse.getProduct().getName(), String.valueOf
                    (productDetailResponse.getProduct().getId()), "1", "", "");
                MyToast.show(getContext(), productDetailResponse.getProduct().getName() +
                    "已添加入购物车中");
                break;
            case R.id.textProdToCollect://收藏
                boolean loginState1 = new SpHelp().getsp("login", false);//判断是否已经登录
                if (!loginState1) {
                    MyToast.show(getContext(), "还没有登录,请先登录");
                    break;
                }
                MyToast.show(getContext(), productDetailResponse.getProduct().getName() +
                    "已添加进入收藏夹");
                break;
            case R.id.relDescription://描述信息

                break;
            case R.id.relProdStock://查看存货

                break;
            case R.id.relProductComment://购买评论

                break;
            case R.id.orderTelTv://订购电话
                Intent intent = new Intent();//创建意图
                //机器语言-->汇编语言-->c语言-->c++语言-->java语言c+++-->c#C++++-->自然语言
                //设置动作,拨打的动作
                intent.setAction(Intent.ACTION_CALL);
                //设置数据， URL http://www.itheima.com 统一资源定位符 一般表示的是网络上的路径
                //http://www.baidu.com ftp://xunlei.com
                //Uri 统一资源定位符， tel://
                intent.setData(Uri.parse("tel://010-88499999"));
                startActivity(intent);

                break;
            case R.id.titleBack://标题栏返回按钮
                Log.i(TAG, "点击了标题栏返回按钮");
                getFragmentManager().popBackStack();
                break;
        }
    }
}
