package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xch on 2016/3/31.
 * HomeFragment 中listview的条目
 */
public class HomeListBaseAdapter extends BaseAdapter {
    private String[] itemStr = new String[]{"限时抢购", "促销快报", "新品上架", "热门单品", "推荐品牌"};
    private int[] itemIcon = new int[]{R.drawable.home_classify_01, R.drawable.home_classify_02, R.drawable.home_classify_03,
            R.drawable.home_classify_04, R.drawable.home_classify_05};


    @Override
    public int getCount() {
        return itemStr.length;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(App.application, R.layout.item_homefragment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivIcon.setImageResource(itemIcon[position]);
        holder.tvTitle.setText(itemStr[position]);
        return convertView;
    }
    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_Title)
        TextView tvTitle;
        @InjectView(R.id.iv_more)
        ImageView ivMore;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

