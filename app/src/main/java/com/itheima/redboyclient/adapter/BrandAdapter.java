package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.PromBulletinActivity;
import com.itheima.redboyclient.bean.BrandResponse;
import com.itheima.redboyclient.bean.PromResponse;
import com.itheima.redboyclient.fragment.BrandFragment;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/1.
 */
public class BrandAdapter extends BaseAdapter {

    private BrandResponse brandResponse;
    private Context context;
    private FragmentManager mFragmentManager;
    private BrandFragment mBrandFragment;

    public BrandAdapter(BrandResponse brandResponse, Context context, FragmentManager fragmentManager, BrandFragment brandFragment) {
        this.brandResponse = brandResponse;
        this.context = context;
        this.mBrandFragment = brandFragment;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return brandResponse.getBrand().size();
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
            convertView = View.inflate(context, R.layout.brand_item,null);
            holder.tvDivision = (TextView) convertView.findViewById(R.id.tv_division);
            holder.gvDivision = (GridView) convertView.findViewById(R.id.gv_division);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        String key = brandResponse.getBrand().get(position).getKey();
        holder.tvDivision.setText(key);
        holder.gvDivision.setAdapter(new BrandGridAdapter(brandResponse,context,position,mFragmentManager,mBrandFragment));
        return convertView;
    }

    static class ViewHolder {
        TextView tvDivision;
        GridView gvDivision;
    }
}
