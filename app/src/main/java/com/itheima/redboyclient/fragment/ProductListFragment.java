package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.ProductListAdapter;
import com.itheima.redboyclient.bean.ProductListResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ZCM on 2016/4/3.
 */
public class ProductListFragment extends BaseFragment {


    ProductListResponse mResponse;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_sift)
    TextView tvSift;
    @InjectView(R.id.category_layout)
    RelativeLayout categoryLayout;
    @InjectView(R.id.rb_sale)
    RadioButton rbSale;
    @InjectView(R.id.rb_price)
    RadioButton rbPrice;
    @InjectView(R.id.rb_rankgood)
    RadioButton rbRankgood;
    @InjectView(R.id.rb_ranktime)
    RadioButton rbRanktime;
    @InjectView(R.id.rg_product_list)
    RadioGroup rgProductList;
    @InjectView(R.id.lv_product_list)
    ListView mProductListLV;
    private List<ProductListResponse.ProductlistBean> productlist;
    private ProductListAdapter productListAdapter;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.fragment_product_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
       HttpLoader.get(ConstantsRedBaby.URL_SERVER + "productlist", null, ProductListResponse.class, ConstantsRedBaby.REQUEST_CODE_PRODUCT_LIST, new HttpLoader.ResponseListener() {
           @Override
           public void onGetResponseSuccess(int requestCode, RBResponse response) {
               ALog.i("请求成功");
               ProductListResponse productListResponse = (ProductListResponse) response;
               productlist = productListResponse.getProductlist();
               productListAdapter = new ProductListAdapter(productlist);
               mProductListLV.setAdapter(productListAdapter);
           }

           @Override
           public void onGetResponseError(int requestCode, VolleyError error) {
               ALog.i("请求失败");
           }
       });
        // 排序的点击事件
        rgProductList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_rankgood:
                        Collections.sort(productlist, new Comparator<ProductListResponse.ProductlistBean>() {
                            @Override
                            public int compare(ProductListResponse.ProductlistBean lhs, ProductListResponse.ProductlistBean rhs) {
                                int num = rhs.getComment_count() - lhs.getComment_count();
                                return num == 0 ? -1 : num;
                            }
                        });
                        break;
                }
                productListAdapter.notifyDataSetChanged();
            }
        });
        mProductListLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.rl_home_fragment, new ProductDetailFragment(productlist.get(position).getId()));
                ft.addToBackStack("HomeFragment");
                ft.commit();*/
                changeFragment(new ProductDetailFragment(productlist.get(position).getId()), "HomeFragment");
            }
        });
    }

    @Override
    public boolean onBackPressed() {

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 从商品列表跳转到其它界面的方法
     *
     * @param mFragment 将要跳转到的fragment
     * @param stackName 回退栈的名字
     * @author zcm
     */
    private void changeFragment(BaseFragment mFragment, String stackName) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.rl_home_fragment, mFragment);
        ft.addToBackStack(stackName);
        ft.commit();
    }

    @OnClick({R.id.tv_back, R.id.tv_sift, R.id.rb_sale, R.id.rb_price, R.id.rb_rankgood, R.id.rb_ranktime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back://调到
                getFragmentManager().popBackStack();
                onBackPressed();
                break;
            case R.id.tv_sift:
                break;
            case R.id.rb_sale:
                break;
            case R.id.rb_price:
                break;
            case R.id.rb_rankgood:
                break;
            case R.id.rb_ranktime:
                break;
        }
    }
}
