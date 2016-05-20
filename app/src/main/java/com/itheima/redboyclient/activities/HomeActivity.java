package com.itheima.redboyclient.activities;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.CategoryResponse;
import com.itheima.redboyclient.bean.GoodsBean;
import com.itheima.redboyclient.dao.DbUtils;
import com.itheima.redboyclient.fragment.BaseFragment;
import com.itheima.redboyclient.fragment.CartFragment;
import com.itheima.redboyclient.fragment.ClassifyFragment;
import com.itheima.redboyclient.fragment.HomeFragment;
import com.itheima.redboyclient.fragment.MoreFragment;
import com.itheima.redboyclient.fragment.PromFragmentDialog;
import com.itheima.redboyclient.fragment.SearchFragment;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p/>
 * 首页
 */
public class HomeActivity extends BaseActivity implements BaseFragment.BackHandledInterface {
    private static final String TAG = "HomeActivity";
    @InjectView(R.id.imgHome)
    public ImageView imgHome;
    @InjectView(R.id.imgClassify)
    public ImageView imgClassify;
    @InjectView(R.id.imgSearch)
    public  ImageView imgSearch;
    @InjectView(R.id.imgShoppingCar)
    public ImageView imgShoppingCar;
    @InjectView(R.id.imgMore)
    public  ImageView imgMore;
    @InjectView(R.id.linToolBar)
    LinearLayout linToolBar;
    @InjectView(R.id.textShopCarNum)
    TextView textShopCarNum;


    private BaseFragment mBackHandedFragment;
    private Fragment mFragment;
    /**
     * 进入HomeActivity显示的FragmentDialog
     */
    public static PromFragmentDialog pfd;
    private HomeFragment homeFragment;


    private static List<CategoryResponse.CategoryEntity> categoryList;

    public List<CategoryResponse.CategoryEntity> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryResponse.CategoryEntity> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * @author ZCM
     * 清理分类数据
     */
    public void clearCategoryData() {
        if (categoryList != null) categoryList.clear();
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }

    }

    @Override
    protected int initContentView() {
        return R.layout.home_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        imgHome.setSelected(true);
        pfd = new PromFragmentDialog();
        pfd.show(getSupportFragmentManager(), "PromFragmentDialog");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        ft.add(R.id.rl_home_fragment, homeFragment);
//        ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.rl_home_fragment, new HomeFragment());
//        ft.addToBackStack("HomeFragment");//这里不能添加到FragmentStack否则主页面按回退键会显示白板

        ft.commit();
        //注册内容观察者
        Uri uri = Uri.parse("content://com.itheima.redboyclient/goods");
        final DbUtils dbUtils = new DbUtils(getApplication());
        List<GoodsBean> goodsBeans = dbUtils.queryAll();
        int goodsCount = 0;
        for (GoodsBean goodsBean :
                goodsBeans) {
            goodsCount = goodsCount + Integer.parseInt(goodsBean.getCount());
        }
        textShopCarNum.setText(String.valueOf(goodsCount));
        getContentResolver().registerContentObserver(uri, true, new ContentObserver(new Handler()) {

            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                System.out.println("我是观察者,我发现商品的数据库变化了.");

                List<GoodsBean> goodsBeans = dbUtils.queryAll();
                int goodsCount = 0;
                for (GoodsBean goodsBean :
                        goodsBeans) {
                    goodsCount = goodsCount + Integer.parseInt(goodsBean.getCount());
                }
                textShopCarNum.setText(String.valueOf(goodsCount));
            }

        });

    }

    @OnClick({R.id.imgHome, R.id.imgClassify, R.id.imgSearch, R.id.imgShoppingCar, R.id.imgMore})
    public void onClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        /**
         * 每次点击主页面下面五个分类，清空前面所有的fragment任务栈
         */
        int n = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < n; i++) {
            FragmentManager.BackStackEntry backstatck = getSupportFragmentManager().getBackStackEntryAt(i);
            System.out.println("Fragment" + backstatck.getName());
            getSupportFragmentManager().popBackStack();
        }

        switch (view.getId()) {
            case R.id.imgHome:// 首页
                imgHome.setSelected(true);
                imgClassify.setSelected(false);
                imgSearch.setSelected(false);
                imgShoppingCar.setSelected(false);
                imgMore.setSelected(false);

                if (mFragment instanceof HomeFragment) {
                    return;
                }
                mFragment = new HomeFragment();
                ft.replace(R.id.rl_home_fragment, mFragment);
                ft.addToBackStack("HomeFragment");
                ft.commit();
                break;
            case R.id.imgClassify:// 分类
                imgHome.setSelected(false);
                imgClassify.setSelected(true);
                imgSearch.setSelected(false);
                imgShoppingCar.setSelected(false);
                imgMore.setSelected(false);
                ft.add(R.id.rl_home_fragment, new ClassifyFragment());
                ft.addToBackStack("ClassifyFragment");
                ft.hide(homeFragment);
                ft.addToBackStack("ClassifyFragment");
                ft.commit();
                break;
            case R.id.imgSearch:// 搜索
                imgHome.setSelected(false);
                imgClassify.setSelected(false);
                imgSearch.setSelected(true);
                imgShoppingCar.setSelected(false);
                imgMore.setSelected(false);

                ft.add(R.id.rl_home_fragment, new SearchFragment());
                ft.addToBackStack("SearchFragment");
                ft.hide(homeFragment);
                ft.commit();
                break;
            case R.id.imgShoppingCar:// 购物车
                imgHome.setSelected(false);
                imgClassify.setSelected(false);
                imgSearch.setSelected(false);
                imgShoppingCar.setSelected(true);
                imgMore.setSelected(false);
                ft.add(R.id.rl_home_fragment, new CartFragment());
                ft.addToBackStack("CartFragment");
                ft.hide(homeFragment);
                ft.commit();
                break;
            case R.id.imgMore:// 更多
                imgHome.setSelected(false);
                imgClassify.setSelected(false);
                imgSearch.setSelected(false);
                imgShoppingCar.setSelected(false);
                imgMore.setSelected(true);
                ft.add(R.id.rl_home_fragment, new MoreFragment());
                ft.addToBackStack("MoreFragment");
                ft.hide(homeFragment);
                ft.commit();
//                Intent intent = new Intent(this,Test.class);
//                startActivity(intent);
                break;
        }
    }

    public void cancel(View view) {
        Log.i(TAG, "取消FragmentDialog");
        pfd.dismiss();
    }

}
