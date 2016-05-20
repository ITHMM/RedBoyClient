package com.itheima.redboyclient.fragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.LimitBuyAdapter;
import com.itheima.redboyclient.adapter.TopicPlistAdapter;
import com.itheima.redboyclient.bean.LimitBuyResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.TopicPlistResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;


/**
 * Created by 李正春 on 2016/4/1.
 * 商品专题fragment
 */
public class TopicPlistFragment extends BaseFragment implements HttpLoader.ResponseListener {
    /**
     * 限时抢购的ListView
     */
    private ListView mLvTopicplist;

    @Override
    public View CreateView() {
        View view = View.inflate(getContext(), R.layout.fragment_topicplist, null);
        mLvTopicplist = (ListView) view.findViewById(R.id.lv_topicplist);
        return view;
    }

    @Override
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_TOPICPLIST, null, LimitBuyResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPICPLIST, this, true);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        TopicPlistResponse topicPlistResponse = (TopicPlistResponse) response;
        mLvTopicplist.setAdapter(new TopicPlistAdapter(topicPlistResponse, getContext()));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
