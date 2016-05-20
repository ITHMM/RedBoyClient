package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.Area;

import java.util.ArrayList;

/**
 * Created by xiaoyan on 2016/4/4.
 */
public class AddressAdapter  extends BaseAdapter{
    private String selectedText = "";
    private ArrayList<Area> mListData;
    private Context mContext;

    public AddressAdapter(Activity mActivity, ArrayList<Area> province) {
        mListData = province;
        mContext = mActivity;
    }

    @Override
    public int getCount() {
        return mListData.size();
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
        TextView view;
        if (convertView == null) {
            view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.choose_item, parent, false);
        } else {
            view = (TextView) convertView;
        }
        view.setTag(position);
        String mString = "";
        if (mListData != null) {
            if (position < mListData.size()) {
                mString = mListData.get(position).getName();
            }
        }
        if (mString.contains("不限"))
            view.setText("不限");
        else
            view.setText(mString);
        view.setTextSize(18);

        if (selectedText != null && selectedText.equals(mString)) {
            view.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.choose_item_selected));//设置选中的背景图�?
        } else {
            view.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.choose_eara_item_selector));//设置未�?中状态背景图�?
        }
        view.setPadding(20, 0, 0, 0);
        return view;
    }
}
