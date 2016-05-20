package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;
import com.itheima.redboyclient.adapter.CategoryThirdAdapter;
import com.itheima.redboyclient.bean.CategoryResponse;

import org.seny.android.utils.ALog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ZCM on 2016/4/1.
 */
public class ClassifyThirdFragment extends BaseFragment {
    @InjectView(R.id.thirdTitleBack)
    TextView thirdTitleBack;
    @InjectView(R.id.categoryThirdTitle)
    TextView mCategoryThirdTitle;
    @InjectView(R.id.categoryThirdList)
    ListView mCategoryThirdList;
    CategoryResponse.CategoryEntity secondEntity;
    private ArrayList<CategoryResponse.CategoryEntity> thirdList;
    private CategoryThirdAdapter mAdapter;


    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.category_child2_activity, null);
        ButterKnife.inject(this, view);
        // 将二级分类传递过来的数据对象取出
        secondEntity = (CategoryResponse.CategoryEntity) getArguments().getSerializable("category2");
        // 获取HomeActivity中所有分类数据集合
        HomeActivity mActivity = (HomeActivity) getActivity();
        List<CategoryResponse.CategoryEntity> categoryAllList = mActivity.getCategoryList();
        if (secondEntity != null) {
            thirdList = new ArrayList<>();
            for (int i = 0; i < categoryAllList.size(); i++) {
                if (secondEntity.getId() == categoryAllList.get(i).getParent_id()) {
                    thirdList.add(categoryAllList.get(i));
                }
            }
        }
        ALog.i("三级分类条目数:" + thirdList.size());
        // 设置三级分类的标题(二级分类条目的Name)
        mCategoryThirdTitle.setText(secondEntity.getName());
        return view;
    }

    @Override
    public void initData() {
        mAdapter = new CategoryThirdAdapter(mActivity, thirdList);
        mCategoryThirdList.setAdapter(mAdapter);
        // 二级分类listview条目的点击事件
        mCategoryThirdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeFragment(position);
            }
        });
    }

    /**
     * 三级分类fragment跳转到商品列表fragment的方法
     */
    private void changeFragment(int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ProductListFragment productListFragment = new ProductListFragment();
        /*// 创建bundle对象，将当前点击条目包含的商品列表数据存储起来
        Bundle bundle = new Bundle();
        // categoryFirstList.get(position)获取对象的时候，要求CategoryEntity实现Serializable接口
        bundle.putSerializable("category2", secondList.get
            (position));
        classifyThirdFragment.setArguments(bundle);*/
        fragmentTransaction.add(R.id.rl_category_fragment_third, productListFragment);
        // 给fragment跳转添加任务栈，保证任务栈的名字一样，即所有fragment在同一个任务栈里面跳转。
        fragmentTransaction.addToBackStack("ClassifyFragment");
//        fragmentTransaction.hide(this);//隐藏当前fragment
        fragmentTransaction.commit();
//        mActivity.clearCategoryData();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState) {
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
     * 三级分类返回按钮的点击事件
     */
    @OnClick(R.id.thirdTitleBack)
    public void onClick() {
        getActivity().getSupportFragmentManager().popBackStack();
        /*FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ClassifySecFragment classifySecFragment = new ClassifySecFragment();
        fragmentTransaction.add(R.id.rl_category_fragment_third, classifySecFragment);
        fragmentTransaction.addToBackStack("ClassifyFragment");
        fragmentTransaction.hide(ClassifyThirdFragment.this);//隐藏当前fragment
        fragmentTransaction.commit();*/
    }
}
