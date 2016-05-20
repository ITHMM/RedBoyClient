package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;
import com.itheima.redboyclient.adapter.CategoryFirstAdapter;
import com.itheima.redboyclient.bean.CategoryResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.seny.android.utils.ALog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZCM on 2016/3/30.
 */
public class ClassifyFragment extends BaseFragment {
    @InjectView(R.id.categoryList)
    ListView mCategoryList;
    CategoryResponse mResponse;
    CategoryFirstAdapter mAdpater;
    private List<CategoryResponse.CategoryEntity> categoryAllList;// 所有分类的数据集合
    public static List<CategoryResponse.CategoryEntity> categoryFirstList;// 一级分类的数据集合
    private ClassifyFragment thisFragment;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.category_activity, null);
        ButterKnife.inject(this, view);
        thisFragment = this;
        return view;
    }

    /**
     * 请求网络初始化数据
     */
    public void initData() {
        HttpLoader.get(ConstantsRedBaby.URL_CATEGORY_LIST, null, CategoryResponse.class,
            ConstantsRedBaby.REQUEST_CODE_CATEGORY_LIST, new HttpLoader.ResponseListener() {
                @Override
                public void onGetResponseSuccess(int requestCode, RBResponse response) {
                    mResponse = (CategoryResponse) response;//请求成功，返回分类数据
                    ALog.i("分类数据请求成功.....");
                    if (thisFragment == null || thisFragment.isDetached())
                        return;
                    getFirstList();
                    switch (requestCode) {
                        case ConstantsRedBaby.REQUEST_CODE_CATEGORY_LIST:
                            if (mAdpater == null) {// mResponse对象要传到适配器参数中
                                mAdpater = new CategoryFirstAdapter(mActivity, categoryFirstList,
                                    mResponse);
                                mCategoryList.setAdapter(mAdpater);
                            } else {
                                mAdpater.notifyDataSetChanged();
                            }
                            // 调用一级分类条目的点击事件
                            firstCategoryOnItemClick();
                            break;
                    }
                }

                @Override
                public void onGetResponseError(int requestCode, VolleyError error) {
                    Toast.makeText(mActivity, "网络连接错误", Toast.LENGTH_SHORT).show();
                }
            });
    }


    // 获取一级分类的集合
    public List<CategoryResponse.CategoryEntity> getFirstList() {
        categoryAllList = mResponse.getCategory();
        // 将所有分类数据的集合categoryAllList存储到HomeActivity中，用于传递到下一级分类中
        HomeActivity mActivity = (HomeActivity) getActivity();
        mActivity.setCategoryList(categoryAllList);
        categoryFirstList = new ArrayList<>();
        for (int i = 0; i < categoryAllList.size(); i++) {
            if ((categoryAllList.get(i).getParent_id()) == 0) {
                // 将parent_id为0的对象添加到一级分类集合中
                categoryFirstList.add(categoryAllList.get(i));
            }
        }
        ALog.i("一级分类条目数：" + categoryFirstList.size());
        return categoryFirstList;
    }

    /**
     * 一级分类listview条目的点击事件
     */

    private void firstCategoryOnItemClick() {
        mCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeFragment(position);
            }
        });
    }

    /**
     * 点击一级分类条目跳转到二级分类fragment
     *
     * @param position 当前被点击的条目位置
     */
    private void changeFragment(int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ClassifySecFragment classifySecFragment = new ClassifySecFragment();
        // 创建bundle对象，将当前点击条目包含的二级分类数据存储起来
        Bundle bundle = new Bundle();
        // categoryFirstList.get(position)获取对象的时候，要求CategoryEntity实现Serializable接口
        bundle.putSerializable("category", categoryFirstList.get(position));
        classifySecFragment.setArguments(bundle);
        // 将事件添加到任务栈
        fragmentTransaction.add(R.id.rl_category_fragment, classifySecFragment);
        // 给fragment添加回退任务栈，让任务栈名字一样，保证所有fragment在同一个任务栈里面跳转。
        fragmentTransaction.addToBackStack(null);
//        fm.putFragment(mActivity.getFragmentBundle(),"ClassifyFragment",this);
//        fragmentTransaction.hide(this);// 隐藏当前fragment
        fragmentTransaction.commit();
//        mActivity.clearCategoryData();
    }

    @Override
    public boolean onBackPressed() {
        if(getActivity()!=null){
            ((HomeActivity)getActivity()).imgHome.setSelected(true);
            ((HomeActivity)getActivity()).imgClassify.setSelected(false);
        }
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
}
