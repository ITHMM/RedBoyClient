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
 * 三级分类的数据适配器
 * Created by ZCM on 2016/3/31.
 */
public class CategoryThirdAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryResponse.CategoryEntity> categoryThirdList;

    public CategoryThirdAdapter(Context context, List<CategoryResponse.CategoryEntity> categoryThirdList) {
        this.context = context;
        this.categoryThirdList = categoryThirdList;
    }

    @Override
    public int getCount() {
        if (categoryThirdList == null) {
            return 0;
        }
        return categoryThirdList.size();
    }

    /**
     * 返回当前条目对象
     *
     * @author ZCM
     * created at 2016/3/31 20:19
     */
    @Override
    public Object getItem(int position) {
        return categoryThirdList.get(position);
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
            view = View.inflate(context, R.layout.category_child2_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        // 获取分类的实体对象
        CategoryResponse.CategoryEntity thirdCategory = categoryThirdList.get(position);
        // 使用volley的imageLoader加载图片
//        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + thirdCategory.getPic(), ImageLoader.getImageListener(holder.imgIcon, R.drawable.product_loading, R.drawable.product_loading));
        holder.textContent.setText(thirdCategory.getName());
        holder.item_describe.setText(thirdCategory.getTag());
        return view;
    }

    static class ViewHolder {
//        @InjectView(R.id.imgIcon)
//        ImageView imgIcon;
        @InjectView(R.id.textContent)
        TextView textContent;
        @InjectView(R.id.item_describe)
        TextView item_describe;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
