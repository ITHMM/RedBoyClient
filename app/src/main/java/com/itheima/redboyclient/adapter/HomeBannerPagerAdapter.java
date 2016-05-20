package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.HomeResponse;
import com.itheima.redboyclient.fragment.ProductDetailFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p/>
 * Created by Seny on 2015/12/1.
 * homefragment中viewpager的适配器
 */
public class HomeBannerPagerAdapter extends PagerAdapter {

    private FragmentActivity activity;
    private HomeResponse homeResponse;

    public HomeBannerPagerAdapter(HomeResponse homeResponse, FragmentActivity activity) {
        this.homeResponse = homeResponse;
        this.activity = activity;
    }

    public void setHomeResponse(HomeResponse homeResponse) {
        this.homeResponse = homeResponse;
    }

    @Override
    public int getCount() {
        if (homeResponse == null || homeResponse.getHome_banner() == null) {
            return 0;
        }
        return homeResponse.getHome_banner().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    // 添加一个条目
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // 获取数据
        final HomeResponse.HomeBannerEntity homeBannerEntity = homeResponse.getHome_banner().get(position);
        // 初始化一个NetworkImageView
        NetworkImageView imageView = new NetworkImageView(App.application);
        // 给imageview设置一个点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ALog.i(homeBannerEntity.getId() + "  ,position :" + position);
                ft.add(R.id.rl_home_fragment, new ProductDetailFragment(homeBannerEntity.getId()));
                ft.addToBackStack("HomeFragment");
                ft.commit();
            }
        });
        // 设置图片填充模式
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 设置网络图片
        imageView.setImageUrl(ConstantsRedBaby.URL_SERVER + homeBannerEntity.getPic(), HttpLoader.getImageLoader());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        container.addView(imageView);
        return imageView;
    }
    // 删除一个条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
