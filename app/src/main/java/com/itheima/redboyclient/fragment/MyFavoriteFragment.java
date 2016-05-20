package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.FavoriteListAdapter;
import com.itheima.redboyclient.bean.FavoriteResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 收藏夹
 * Created by Administrator on 2016/3/31/031.
 */
public class MyFavoriteFragment extends BaseFragment implements HttpLoader.ResponseListener {

    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.fraHead)
    FrameLayout fraHead;
    @InjectView(R.id.myfavorite_product_list)
    ListView myfavoriteProductList;
    @InjectView(R.id.myfavorite_productlist_layout)
    LinearLayout myfavoriteProductlistLayout;
//    @InjectView(R.id.bottomSpace)
//    RelativeLayout bottomSpace;
//    @InjectView(R.id.imgHome)
//    ImageView imgHome;
//    @InjectView(R.id.imgClassify)
//    ImageView imgClassify;
//    @InjectView(R.id.imgSearch)
//    ImageView imgSearch;
//    @InjectView(R.id.imgShoppingCar)
//    ImageView imgShoppingCar;
//    @InjectView(R.id.imgMore)
//    ImageView imgMore;
//    @InjectView(R.id.linToolBar)
//    LinearLayout linToolBar;
//    @InjectView(R.id.textShopCarNum)
//    TextView textShopCarNum;
    private FavoriteListAdapter favoritelistAdapter;
    private FavoriteResponse favorites;

    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.my_favorite_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("page", "1");
        params.put("pageNum", "10");
        HttpLoader.get(ConstantsRedBaby.URL_FAVORITE, params, FavoriteResponse.class, ConstantsRedBaby.REQUEST_CODE_MYFAVORITE, this, false);
    }
    @OnClick(R.id.head_back_text)
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.head_back_text://返回更多
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                break;
        }
    }


    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_MYFAVORITE:
                System.out.println("response"+response);

                favorites = (FavoriteResponse) response;
                if (favoritelistAdapter == null) {
                    favoritelistAdapter = new FavoriteListAdapter(favorites, mActivity);
                    myfavoriteProductList.setAdapter(favoritelistAdapter);
                    /**
                     * 收藏夹条目点击事件
                     */
                    myfavoriteProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.add(R.id.rl_home_fragment, new ProductDetailFragment(favorites.getProductlist().get(position).getId()));
                            transaction.addToBackStack("HomeFragment");
                            transaction.hide(MyFavoriteFragment.this);
                           transaction.commit();
                        }
                    });
                } else {
                    favoritelistAdapter.notifyDataSetChanged();
                }

                break;
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
