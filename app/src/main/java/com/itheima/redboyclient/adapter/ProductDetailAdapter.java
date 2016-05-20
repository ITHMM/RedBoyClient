package com.itheima.redboyclient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.bean.ProductDetailResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李正春 on 2016/4/4.
 * 商品详情的ViewPagerAdapter
 */
public class ProductDetailAdapter extends PagerAdapter {
    ProductDetailResponse mProductDetailResponse;
    /**
     * 图片路径的集合
     */
    private final List<String> mPics;

    public ProductDetailAdapter(ProductDetailResponse productDetailResponse) {
        this.mProductDetailResponse = productDetailResponse;
        List<String> bigPics = mProductDetailResponse.getProduct().getBigPic();
        List<String> pics = mProductDetailResponse.getProduct().getPic();
        mPics = new ArrayList<String>();
        for (String bigPic :
                bigPics) {
            mPics.add(bigPic);
        }
        for (String pic :
                pics) {
            mPics.add(pic);
        }
    }

    @Override
    public int getCount() {
        if (mProductDetailResponse == null || mProductDetailResponse.getProduct() == null) {
            return 0;
        }
        return mPics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String picUrl = mPics.get(position);//获取大图片路径
        //初始化NetWorkImageView
        NetworkImageView imageView = new NetworkImageView(App.application);
        //设置图片填充模式
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageUrl(ConstantsRedBaby.URL_SERVER + picUrl, HttpLoader.getImageLoader());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
