package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.CategoryResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 一级分类的数据适配器
 * Created by ZCM on 2016/3/31.
 */
public class CategoryFirstAdapter extends BaseAdapter {

    private Context context;
    private CategoryResponse mResponse;
    private List<CategoryResponse.CategoryEntity> firstList;


    public CategoryFirstAdapter(Context context, List<CategoryResponse.CategoryEntity> firstList, CategoryResponse mResponse) {
        this.firstList = firstList;
        this.context = context;
        this.mResponse = mResponse;
    }

    @Override
    public int getCount() {// mResponse做非空判断，一定要传到构造参数列表中
        if (mResponse == null || firstList == null) {
            return 0;
        }
        return firstList.size();
    }

    /**
     * 返回当前条目对象
     *
     * @author ZCM
     * created at 2016/3/31 20:19
     */
    @Override
    public Object getItem(int position) {
        return firstList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ALog.e("22222222222222222222222222");
        ViewHolder holder;
        View view;
        if (convertView != null && convertView instanceof RelativeLayout) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.category_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        // 获取分类的实体对象
        CategoryResponse.CategoryEntity firstEntity = firstList.get(position);
        // 使用volley的imageLoader加载图片
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + firstEntity.getPic(), ImageLoader.getImageListener(holder.imgIcon, R.drawable.product_loading, R.drawable.product_loading));
        holder.textContent.setText(firstEntity.getName());
        holder.item_describe.setText(firstEntity.getTag());
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.imgIcon)
        ImageView imgIcon;
        @InjectView(R.id.textContent)
        TextView textContent;
        @InjectView(R.id.item_describe)
        TextView item_describe;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
