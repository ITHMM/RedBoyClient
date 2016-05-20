package com.itheima.redboyclient.adapter;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;
import com.itheima.redboyclient.activities.PromBulletinActivity;
import com.itheima.redboyclient.bean.PromResponse;
import com.itheima.redboyclient.fragment.ProductDetailFragment;
import com.itheima.redboyclient.fragment.PromFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

/**
 * Created by 李正春 on 2016/3/30.
 */
public class PromAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "PromAdapter";
    /**
     * 热销快报Javabean
     */
    private PromResponse mProm;
    private Context context;
    private FragmentManager mFragmentManager;
    /**
     * 在Adapter中隐藏父Fragment需要通过构造方法传进来
     */
    private PromFragment mPromFragment;


    /**
     * @param prom            热销快报的基类
     * @param context
     * @param fragmentManager
     * @param promFragment
     */
    public PromAdapter(PromResponse prom, Context context, FragmentManager fragmentManager, PromFragment promFragment) {
        mProm = prom;
        this.context = context;
        this.mFragmentManager = fragmentManager;
        this.mPromFragment = promFragment;
    }

    public PromAdapter(PromResponse promResponse, Context context, FragmentManager fragmentManager) {
        mProm = promResponse;
        this.context = context;
        this.mFragmentManager = fragmentManager;
    }

    public PromAdapter(PromResponse proms, Application application, android.app.FragmentManager fragmentManager) {
        mProm = proms;
        this.context = application;
    }

    @Override
    public int getCount() {
        ALog.i("topic:"+mProm.getTopic().size());
        if (mProm == null && mProm.getTopic() == null) {
            return 0;
        }
        return mProm.getTopic().size();
    }

    @Override
    public Object getItem(int position) {
        return mProm.getTopic().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.prom_bulletin_item, null);
            holder = new ViewHolder();
            holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.textContent = (TextView) convertView.findViewById(R.id.textContent);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        PromResponse.TopicEntity topicEntity = mProm.getTopic().get(position);
//使用Volley的ImageLoader加载图片
        ALog.i("name:"+topicEntity.getName());
        ALog.i("url:"+topicEntity.getPic());
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + topicEntity.getPic(),
                ImageLoader.getImageListener(holder.imgIcon, R.drawable.product_loading,
                        R.drawable.product_loading), PromBulletinActivity.mScreenWidth, PromBulletinActivity.mScreenHeight, ImageView.ScaleType.FIT_XY);
        holder.textContent.setText(topicEntity.getName());
        holder.imgIcon.setTag(position);
        holder.imgIcon.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        int position = Integer.parseInt(v.getTag().toString());
        PromResponse.TopicEntity currentPromBean = mProm.getTopic().get(position);
        Log.i(TAG, currentPromBean.getName() + "详情界面------" + v.getTag().toString());
        ft.add(R.id.rl_home_fragment, new ProductDetailFragment(currentPromBean.getId()));
        ft.addToBackStack("HomeFragment");
        if (mPromFragment != null) {
            ft.hide(mPromFragment);
        } else {
            HomeActivity.pfd.dismiss();
        }
        ft.commit();

    }

    static class ViewHolder {
        TextView textContent;
        ImageView imgIcon;
    }
}
