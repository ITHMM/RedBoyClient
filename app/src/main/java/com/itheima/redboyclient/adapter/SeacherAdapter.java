package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.ExpandableListViewBean;
import com.itheima.redboyclient.bean.SearchHotResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xch on 2016/4/5.
 * 搜索页面ExpandableListView的适配器
 */

public class SeacherAdapter extends BaseExpandableListAdapter {
    private List<ExpandableListViewBean> prentList;

    public SeacherAdapter(SearchHotResponse searchHotResponse, List<String> listHistory) {
        prentList = new ArrayList<>();
        ExpandableListViewBean parentBean = new ExpandableListViewBean();
        parentBean.name="热门搜索";
        parentBean.list = searchHotResponse.getSearch_keywords();
        prentList.add(parentBean);

        ExpandableListViewBean parentBean2 = new ExpandableListViewBean();
        parentBean2.name = "历史记录";
        parentBean2.list = listHistory;
        prentList.add(parentBean2);
    }

    // 父布局的个数
    @Override
    public int getGroupCount() {
//        return seacherTypes.length;
        return prentList.size();
    }

    // 获取当前父item下子tiem的个数
    @Override
    public int getChildrenCount(int groupPosition) {
//        return groupPosition;
        return prentList.get(groupPosition).list.size();
    }
    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
//        return seacherTypes[groupPosition];
        return prentList.get(groupPosition);
    }
    // 子item要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
//        return childPosition;
        return prentList.get(groupPosition).list.get(childPosition);
    }
    //父item的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    // 子item的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    // 父布局对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(App.application,R.layout.item_seacher_base,null);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        ImageView iv_isexpanded = (ImageView) convertView.findViewById(R.id.iv_isexpanded);
        if (isExpanded){
            iv_isexpanded.setImageResource(R.drawable.unfold);
        }else{
            iv_isexpanded.setImageResource(R.drawable.arrow);
        }
        ExpandableListViewBean expadableBean = prentList.get(groupPosition);
        tv_title.setText(expadableBean.name);
        return convertView;
    }
    // 子布局对象
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = View.inflate(App.application,R.layout.search_item,null);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_Title);
        String seacher = prentList.get(groupPosition).list.get(childPosition);
        tv_title.setText(seacher);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
