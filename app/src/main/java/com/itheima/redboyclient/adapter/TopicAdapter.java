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
import com.itheima.redboyclient.bean.TopicResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicAdapter extends BaseAdapter {
    TopicResponse mTopic;
    private Context context;

    public TopicAdapter(TopicResponse topic, Context context) {
        mTopic = topic;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (mTopic == null || mTopic.getTopic() == null) {
            return 0;
        }
        return mTopic.getTopic().size();
    }

    @Override
    public Object getItem(int position) {
        return mTopic.getTopic().get(position);
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
            view = View.inflate(context, R.layout.topic_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        final TopicResponse.TopicEntity limitbuy = mTopic.getTopic().get(position);
        //使用Volley的ImageLoader加载图片
        HttpLoader.getImageLoader().get(ConstantsRedBaby.URL_SERVER + limitbuy.getPic(), ImageLoader.getImageListener(holder.mTopicIV, R.drawable.product_loading, R.drawable.product_loading));


        holder.mTopicTV.setText(limitbuy.getName());
        return view;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'topic_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.topic_TV)
        TextView mTopicTV;
        @InjectView(R.id.topic_IV)
        ImageView mTopicIV;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}



