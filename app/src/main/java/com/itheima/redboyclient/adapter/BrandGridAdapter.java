package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.BrandResponse;
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.bean.TopicResponse;
import com.itheima.redboyclient.fragment.BrandFragment;
import com.itheima.redboyclient.fragment.ProductDetailFragment;
import com.itheima.redboyclient.fragment.PromFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/2.
 */
public class BrandGridAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "BrandGridAdapter";
    private BrandResponse brandResponse;
    private Context context;
    /**
     * 当前ListView的第几个条目
     */
    private int listViewPosition;
    /**
     * 推荐品牌详情集合
     */
    private List<BrandResponse.BrandBean.ValueBean> values;
    private FragmentManager mFragmentManager;
    private BrandFragment mBrandFragment;

    public BrandGridAdapter(BrandResponse brandResponse, Context context, int listViewPosition, FragmentManager fragmentManager, BrandFragment brandFragment) {
        this.brandResponse = brandResponse;
        this.context = context;
        this.listViewPosition = listViewPosition;
        this.mBrandFragment = brandFragment;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        List<BrandResponse.BrandBean> brands = brandResponse.getBrand();//专区品牌的集合
        values = brands.get(listViewPosition).getValue();
        return values.size();
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
        Viewholder holder = null;
        if (convertView == null) {
            holder = new Viewholder();
            convertView = View.inflate(context, R.layout.brand_grid_item, null);
            holder.ivGidItem = (ImageView) convertView.findViewById(R.id.iv_grid_item);
            holder.tvGidItem = (TextView) convertView.findViewById(R.id.tv_gid_item);
//            imageView.setLayoutParams(new GridView.LayoutParams(100, 800));//设置ImageView对象布局
//            imageView.setAdjustViewBounds(true);//设置边界对齐
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置刻度的类型
//            imageView.setPadding(2, 2, 2, 2);//设置间距
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.tvGidItem.setText(values.get(position).getName());
        //使用Volley的ImageLoader加载图片
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + values.get(position).getPic(), ImageLoader.getImageListener(holder.ivGidItem, R.drawable.product_loading, R.drawable.product_loading));
        holder.ivGidItem.setTag(position);
        holder.ivGidItem.setOnClickListener(this);//点击图片跳转到商品详情界面
        return convertView;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        int position = Integer.parseInt(v.getTag().toString());
        BrandResponse.BrandBean.ValueBean valueBean = values.get(position);
        Log.i(TAG, "跳转商品详情界面" + valueBean.getName() + "-------" + position);
        ft.add(R.id.rl_home_fragment, new ProductDetailFragment(valueBean.getId()));
        ft.addToBackStack("HomeFragment");
        ft.hide(mBrandFragment);
        ft.commit();
    }

    static class Viewholder {
        ImageView ivGidItem;
        TextView tvGidItem;
    }
}
