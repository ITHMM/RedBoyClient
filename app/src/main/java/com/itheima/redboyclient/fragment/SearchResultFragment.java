package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.SearchResultAdapter;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.bean.SearchResultResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xch on 2016/4/1.
 * 搜索结果页面
 */
public class SearchResultFragment extends BaseFragment {


    /**
     * 搜索结果listview
     */
    ListView lvSearchResult;
    /**
     * 排序方式的选择按钮
     */
    RadioGroup rgSearchSort;
    /**
     * 搜索到的商品列表集合
     */
    private List<SearchResultResponse.ProductlistBean> productlist;
    /**
     * 用来排序的listview
     */
    private List<SearchResultResponse.ProductlistBean> list;
    /**
     * 搜索结果的适配器
     */
    private SearchResultAdapter searchResultAdapter;
    /**
     * 发回上页面按钮
     */
    private TextView tv_back;


    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_search_result, null);
        lvSearchResult = (ListView) view.findViewById(R.id.lv_search_result);
        rgSearchSort = (RadioGroup) view.findViewById(R.id.rg_search_sort);
        tv_back = (TextView) view.findViewById(R.id.tv_back);
        return view;
    }

    @Override
    public void initData() {
        /**
         * 发送网络请求
         */
        HttpLoader.get(ConstantsRedBaby.URL_SEARCH_RESULT, null, SearchResultResponse.class, ConstantsRedBaby.REQUEST_CODE_SEARCH_RESULT, new HttpLoader.ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                SearchResultResponse resultResponse = (SearchResultResponse) response;
                // 获取搜索结果数据
                productlist = resultResponse.getProductlist();
                // 设置适配器
                searchResultAdapter = new SearchResultAdapter(productlist);
                lvSearchResult.setAdapter(searchResultAdapter);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
        lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.rl_home_fragment, new ProductDetailFragment(productlist.get(position).getId()));
                ft.addToBackStack("HomeFragment");
                ft.commit();
            }
        });
        // 排序的点击事件
        rgSearchSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Collections.sort 大于返回1，等于返回0，小于会返回-1
                switch (checkedId) {
                    case R.id.rb_sale://根据销量排序 视id为销量
                        Collections.sort(productlist, new Comparator<SearchResultResponse.ProductlistBean>() {
                            @Override
                            public int compare(SearchResultResponse.ProductlistBean lhs, SearchResultResponse.ProductlistBean rhs) {
                                int num = rhs.getId() - lhs.getId();
                                ALog.i("销量排序" + num);
                                return num == 0 ? -1 : num;
                            }
                        });
                        break;
                    case R.id.rb_price:// 价格升序
                        Collections.sort(productlist, new Comparator<SearchResultResponse.ProductlistBean>() {
                            @Override
                            public int compare(SearchResultResponse.ProductlistBean lhs, SearchResultResponse.ProductlistBean rhs) {
                                int num = lhs.getPrice() - rhs.getPrice();
                                ALog.i("价格升序" + num);
                                return num == 0 ? -1 : num;
                            }
                        });
                        break;
                    case R.id.rb_time:// 价格降序
                        Collections.sort(productlist, new Comparator<SearchResultResponse.ProductlistBean>() {
                            @Override
                            public int compare(SearchResultResponse.ProductlistBean lhs, SearchResultResponse.ProductlistBean rhs) {
                                int num = rhs.getPrice() - lhs.getPrice();
                                return num == 0 ? 1 : num;
                            }
                        });
                        break;
                    case R.id.rb_comment:// 根据评论倒叙
                        Collections.sort(productlist, new Comparator<SearchResultResponse.ProductlistBean>() {
                            @Override
                            public int compare(SearchResultResponse.ProductlistBean lhs, SearchResultResponse.ProductlistBean rhs) {
                                int num = rhs.getComment_count() - lhs.getComment_count();
                                return num == 0 ? 1 : num;
                            }
                        });
//                        for (SearchResultResponse.ProductlistBean productlistBean : productlist) {
//                            ALog.i(productlistBean.getComment_count()+"");
//                        }
                        break;

                }
                searchResultAdapter.notifyDataSetChanged();
            }
        });
        // 返回上个页面的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
