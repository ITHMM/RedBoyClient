package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;
import com.itheima.redboyclient.adapter.SeacherAdapter;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.SearchHotResponse;
import com.itheima.redboyclient.dao.SeacherHistoryDao;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;
import org.seny.android.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xch on 2016/3/30.
 * 搜索页面
 */
public class SearchFragment extends BaseFragment {

    /**
     * 搜索页面的ExpandableListView
     */
    @InjectView(R.id.expand_listview)
    ExpandableListView expandListview;
    @InjectView(R.id.keyWordEdit)
    EditText keyWordEdit;
    /**
     * 搜索历史的数据库操作对象
     */
    private SeacherHistoryDao dao;
    List<String> listHistory = new ArrayList<String>();
    private SeacherAdapter seacherAdapter;
    private SearchHotResponse searchHotResponse;

    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.search_fragment, null);
        return view;
    }

    @Override
    public void initData() {
        dao = new SeacherHistoryDao(getActivity());
        List<String> seacher = dao.findAll();
        listHistory.addAll(seacher);
        // 获取热门搜索的数据
        HttpLoader.get(ConstantsRedBaby.URL_SEARCH_HOT, null, SearchHotResponse.class, ConstantsRedBaby.REQUEST_CODE_SEARCH, new HttpLoader.ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                searchHotResponse = (SearchHotResponse) response;
                // 设置适配器
                seacherAdapter = new SeacherAdapter(searchHotResponse, listHistory);
                expandListview.setAdapter(seacherAdapter);
                // 将所有的条目打开
                int count = expandListview.getCount();
                for (int i = 0; i < count; i++) {
                    expandListview.expandGroup(i);
                }
                // 儿子的点击事件
                expandListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        RelativeLayout layout = (RelativeLayout) getActivity().findViewById(R.id.rl_home_fragment);
                        ft.add(layout.getId(), new SearchResultFragment());
                        ft.addToBackStack("SearchResultFragment");
                        ft.commit();
                        return true;
                    }
                });
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                MyToast.show(getActivity(), "获取数据失败");
            }
        });
//        // 爹的点击事件
//        expandListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                return false;
//            }
//        });

    }

    @Override
    public boolean onBackPressed() {
        if(getActivity()!=null){
            ((HomeActivity)getActivity()).imgHome.setSelected(true);
            ((HomeActivity)getActivity()).imgSearch.setSelected(false);
        }
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    // 搜索的点击事件
    @OnClick(R.id.searchTv)
    public void onClick() {
        // 获取输入的搜索关键字
        String seacher = keyWordEdit.getText().toString().trim();
        // 判断是否为空
        if (TextUtils.isEmpty(seacher)){
            MyToast.show(getActivity(),"不能为空");
            return;
        }
        // 将搜索记录添加到数据库
        dao.addSeacherHistory(seacher);
        // 清空原有集合
        listHistory.clear();
        // 将查询到的所有搜索记录添加到集合
        List<String> seacherlist = dao.findAll();
        listHistory.addAll(seacherlist);
        // 刷新适配器
        seacherAdapter.notifyDataSetChanged();
        // 打开搜索结果的布局
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        RelativeLayout layout = (RelativeLayout) getActivity().findViewById(R.id.rl_home_fragment);
        ft.add(layout.getId(), new SearchResultFragment());
        ft.addToBackStack("SearchResultFragment");
        ft.commit();
        // 将输入框里面的内容清空
        keyWordEdit.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
