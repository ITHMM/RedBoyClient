package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.CategoryResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 二级分类的数据适配器
 * Created by ZCM on 2016/3/31.
 */
public class CategorySecAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryResponse.CategoryEntity> categorySecList;

    public CategorySecAdapter(Context context, List<CategoryResponse.CategoryEntity>
        categorySecList) {
        this.context = context;
        this.categorySecList = categorySecList;
    }


    @Override
    public int getCount() {
        if (categorySecList == null) {
            return 0;
        }
        return categorySecList.size();
    }

    /**
     * 返回当前条目对象
     *
     * @author ZCM
     * created at 2016/3/31 20:19
     */
    @Override
    public Object getItem(int position) {
        return categorySecList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView != null && convertView instanceof RelativeLayout) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.category_child_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        // 获取分类的实体对象
        CategoryResponse.CategoryEntity secEntity = categorySecList.get(position);
        // 使用volley的imageLoader加载图片
//        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + secEntity.getPic(), ImageLoader.getImageListener(holder.imgIcon, R.drawable.product_loading, R.drawable.product_loading));
//        ALog.i("name:"+secEntity.getName());
//        ALog.i("Tag:"+secEntity.getTag());
        holder.childTextContent.setText(secEntity.getName());
        holder.child_item_describe.setText(secEntity.getTag());
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.childTextContent)
        TextView childTextContent;
        @InjectView(R.id.child_item_describe)
        TextView child_item_describe;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
