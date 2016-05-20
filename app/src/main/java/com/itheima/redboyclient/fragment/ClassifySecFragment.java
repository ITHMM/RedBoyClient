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
import com.itheima.redboyclient.adapter.CategorySecAdapter;
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
public class ClassifySecFragment extends BaseFragment {
    @InjectView(R.id.secTitleBack)
    TextView secTitleBack;
    @InjectView(R.id.categorySecList)
    ListView mCategorySecList;
    @InjectView(R.id.categorySecTitle)
    TextView mCategorySecTitle;
    /**
     * 二级分类的数据适配器
     */
    private CategorySecAdapter mAdapter;
    private List<CategoryResponse.CategoryEntity> secondList;
    CategoryResponse.CategoryEntity firstEntity;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.category_child_activity, null);
        ButterKnife.inject(this, view);
        // 将一级分类传递过来的数据对象取出
        firstEntity = (CategoryResponse.CategoryEntity) getArguments().getSerializable("category");
        // 获取HomeActivity中所有分类数据集合
        HomeActivity mActivity = (HomeActivity) getActivity();
        List<CategoryResponse.CategoryEntity> categoryAllList = mActivity.getCategoryList();
        if (firstEntity != null) {// 将二级分类集合secondList从所有分类集合中取出
            secondList = new ArrayList<>();
            for (int i = 0; i < categoryAllList.size(); i++) {
                if (firstEntity.getId() == categoryAllList.get(i).getParent_id()) {
                    secondList.add(categoryAllList.get(i));
                }
            }
        }
        ALog.i("二级分类条目数:" + secondList.size());
        // 设置二级分类的标题(一级分类对应条目的Name)
        mCategorySecTitle.setText(firstEntity.getName());
//        FragmentManager fm = mActivity.getSupportFragmentManager();
//
//        Fragment classifyFragment = fm.getFragment(mActivity.getFragmentBundle(), "ClassifyFragment");
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.hide(classifyFragment);
//        ft.commit();
        return view;
    }

    @Override
    /**
     * 初始化数据
     */
    public void initData() {
        mAdapter = new CategorySecAdapter(mActivity, secondList);
        mCategorySecList.setAdapter(mAdapter);
        // 二级分类listview条目的点击事件
        mCategorySecList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeFragment(position);
            }
        });
    }

    /**
     * 二级分类fragment跳转到三级分类fragment的方法
     */
    private void changeFragment(int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ClassifyThirdFragment classifyThirdFragment = new ClassifyThirdFragment();
        // 创建bundle对象，将当前点击条目包含的三级分类数据存储起来
        Bundle bundle = new Bundle();
        // categoryFirstList.get(position)获取对象的时候，要求CategoryEntity实现Serializable接口
        bundle.putSerializable("category2", secondList.get(position));
        classifyThirdFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.rl_category_fragment_sec, classifyThirdFragment);
        // 给fragment跳转添加任务栈，保证任务栈的名字一样，即所有fragment在同一个任务栈里面跳转。
        fragmentTransaction.addToBackStack("ClassifyFragment");
//        fragmentTransaction.hide(this);// 隐藏当前fragment
        fragmentTransaction.commit();
//        mActivity.clearCategoryData();
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
     * 二级分类返回按钮的点击事件
     */
    @OnClick(R.id.secTitleBack)
    public void onClick() {
        getActivity().getSupportFragmentManager().popBackStack();
        /*FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ClassifyFragment classifyFragment = new ClassifyFragment();
        fragmentTransaction.add(R.id.rl_category_fragment_sec, classifyFragment);
        fragmentTransaction.addToBackStack("ClassifyFragment");
        fragmentTransaction.hide(ClassifySecFragment.this);// 隐藏当前fragment
        fragmentTransaction.commit();*/
    }
}
