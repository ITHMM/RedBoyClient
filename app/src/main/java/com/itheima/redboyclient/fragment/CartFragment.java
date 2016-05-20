package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.HomeActivity;
import com.itheima.redboyclient.bean.GoodsBean;
import com.itheima.redboyclient.dao.DbUtils;

import org.seny.android.utils.MyToast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ly on 2016/3/31.
 */
public class CartFragment extends BaseFragment implements View.OnClickListener {
    private static final int TURN_HOME = 0;
    private static final int TURN_PAY = 1;
    private static final int EDIT = 2;
    private static final int DELETE_PRODUCT = 3;
    private static final String TAG = "CartFragment";
    private int sum;
    private boolean checked = false;

    private DbUtils dao;
    private List<GoodsBean> goodsBeans;
    private MyRecycleAdapter adapter;
    private boolean flag = false;
    /**
     * 顶部栏的修改和去结算按钮
     */
    @InjectView(R.id.shopcar_update_text)
    TextView shopcarUpdateText;
    @InjectView(R.id.shopcar_toPay_text)
    TextView shopcarToPayText;
    /**
     * 购物车为空时中部显示的相关控件
     */
    @InjectView(R.id.shopcar_default_img)
    ImageView shopcarDefaultImg;
    @InjectView(R.id.shopcar_null_text)
    TextView shopcarNullText;
    @InjectView(R.id.shopcar_toBuy_text)
    TextView shopcarToBuyText;
    /**
     * 中部RecycleView
     */
    @InjectView(R.id.rl_cart_container)
    RelativeLayout rlCartContainer;
    @InjectView(R.id.rv_cart)
    RecyclerView rvCart;
    /**
     * 底部栏的相应控件
     */
    @InjectView(R.id.rl_cart_bottom)
    RelativeLayout rlCartBottom;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.txt_total)
    TextView txtTotal;
    @InjectView(R.id.btn_order)
    Button btnOrder;
    @InjectView(R.id.btn_del)
    Button btnDel;


    @Override
    public View CreateView() {
        View view = View.inflate(getActivity(), R.layout.shopping_car_activity, null);
        ButterKnife.inject(this, view);

        return view;
    }


    @Override
    public void initData() {
        //读取数据库
        dao = new DbUtils(mActivity);
        //商品信息列表
        goodsBeans = dao.queryAll();
        initView();

        //处理RecycleView
        disposeRecycleView();
        getTotalPrice();
        txtTotal.setText("￥" + sum);
    }

    /**
     * 获得价格总计
     */
    private void getTotalPrice() {
        for (GoodsBean goodsBean : goodsBeans) {
            int price = Integer.parseInt(goodsBean.getCount()) * 800;
            sum += price;
        }
    }

    private void disposeRecycleView() {
        //1代表竖直排列,0代表水平排列,false代表不翻转布局
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getActivity(), 1, false);
        rvCart.setLayoutManager(layoutmanager);
        adapter = new MyRecycleAdapter();
        rvCart.setAdapter(adapter);
    }


    private void initView() {
        if (goodsBeans.size() == 0) {
            MyToast.show(getActivity(), "购物车空空如也,随便逛逛吧~");
            shopcarUpdateText.setVisibility(View.GONE);
            shopcarToPayText.setVisibility(View.GONE);
            shopcarToBuyText.setOnClickListener(this);
            shopcarToBuyText.setTag(TURN_HOME);

            shopcarDefaultImg.setVisibility(View.VISIBLE);
            shopcarNullText.setVisibility(View.VISIBLE);
            shopcarToBuyText.setVisibility(View.VISIBLE);

            rlCartContainer.setVisibility(View.GONE);
            rlCartBottom.setVisibility(View.GONE);
        } else {
            //设置相关控件的显示和隐藏
            shopcarUpdateText.setVisibility(View.VISIBLE);
            shopcarUpdateText.setOnClickListener(this);
            shopcarUpdateText.setTag(EDIT);
            shopcarToPayText.setVisibility(View.VISIBLE);
            shopcarToPayText.setOnClickListener(this);
            shopcarToPayText.setTag(TURN_PAY);

            shopcarDefaultImg.setVisibility(View.GONE);
            shopcarNullText.setVisibility(View.GONE);
            shopcarToBuyText.setVisibility(View.GONE);

            rlCartContainer.setVisibility(View.VISIBLE);
            rlCartBottom.setVisibility(View.VISIBLE);
//            checkboxAll.setEnabled(true);
            btnOrder.setOnClickListener(this);
            btnOrder.setTag(TURN_PAY);
        }

    }

    @Override
    public boolean onBackPressed() {
        if (getActivity() != null) {
            ((HomeActivity) getActivity()).imgHome.setSelected(true);
            ((HomeActivity) getActivity()).imgShoppingCar.setSelected(false);
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        RelativeLayout rl = (RelativeLayout) getActivity().findViewById(R.id.rl_home_fragment);
        switch ((int) v.getTag()) {
            case 0:
                //跳转到首页
                ft.add(rl.getId(), new HomeFragment());
                HomeActivity activity = (HomeActivity) getActivity();
                if (activity.imgHome != null) {
                    activity.imgHome.setSelected(true);
                    activity.imgShoppingCar.setSelected(false);
                }
                ft.commit();
                break;
            case 1:
                //跳转到结算中心
                ft.add(rl.getId(), new PayCenterFragment(sum));//结算
                ft.addToBackStack("PayCenterFragment");
                ft.commit();
                break;
            case 2:
                //点击编辑页面,去掉所有勾选
                checkboxAll.setChecked(flag);
                if (!flag) {
                    btnDel.setVisibility(View.VISIBLE);
                    btnDel.setOnClickListener(this);
                    btnDel.setTag(DELETE_PRODUCT);
                } else {
                    btnDel.setVisibility(View.GONE);
                }
                int itemCount1 = rvCart.getAdapter().getItemCount();
                for (int i = 0; i < itemCount1; i++) {
                    View rvCartChildAt = rvCart.getChildAt(i);
                    if (rvCartChildAt == null) {
                        break;
                    }
                    CheckBox cb_cart = (CheckBox) rvCartChildAt.findViewById(R.id.cb_cart);
                    cb_cart.setChecked(checked);
                }

                flag = !flag;
                checked = !checked;
                break;
            case 3:         //删除商品
                int itemCount2 = rvCart.getAdapter().getItemCount();

                for (int i = 0; i < itemCount2; i++) {
                    View rvCartChildAt = rvCart.getChildAt(i);
                    CheckBox cb_cart = (CheckBox) rvCartChildAt.findViewById(R.id.cb_cart);
                    boolean cb_cartChecked = cb_cart.isChecked();
                    if (cb_cartChecked) {
                        TextView prodName = (TextView) rvCartChildAt.findViewById(R.id.shopcar_item_prodName_text);
                        CharSequence name = prodName.getText();
                    }
                }
                dao.deleteAAll();
                goodsBeans.clear();
                sum = 0;
                txtTotal.setText("￥" + sum);
                rvCart.getAdapter().notifyDataSetChanged();
                initView();
                break;
        }
    }

    public class MyRecycleAdapter extends RecyclerView.Adapter {

        @Override
        public int getItemCount() {
            return goodsBeans.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), R.layout.shopping_car_listitem, null);
//            int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childAt = parent.getChildAt(i);
//                CheckBox cbCart1 = (CheckBox) childAt.findViewById(R.id.cb_cart);
//                cbCart1.setChecked(true);   //全选
//                cbCart1.setChecked(!cbCart1.isChecked());   //反选
//            }
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder mvh = ((MyViewHolder) holder);
            GoodsBean goodsBean = goodsBeans.get(position);
            mvh.shopcarItemProdNameText.setText(goodsBean.getName());
//            String price = product.getPrice();
//            int i = Integer.valueOf(price).intValue();
//            sum += i;
//            mvh.shopcarItemSubPriceText.setText(goodsBean.getPrice());
            mvh.shopcarItemSubPriceText.setText("800");
            mvh.shopcarItemProdColorText.setText(goodsBean.getColor());
            mvh.shopcarItemProdSizeText.setText(goodsBean.getSize());
            mvh.shopcarItemProdImageImg.setImageResource(R.drawable.product_03);
            mvh.shopcarItemProdCountText.setText(goodsBean.getCount());
            mvh.cbCart.setChecked(true);

            mvh.itemView.setTag(mvh);
            mvh.itemView.setOnClickListener(clickListener);


        }

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MyViewHolder mvh = (MyViewHolder) v.getTag();
            int adapterPosition = mvh.getAdapterPosition();

            mvh.cbCart.setChecked(checked);
            checked = !checked;
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.cb_cart)
        CheckBox cbCart;
        @InjectView(R.id.shopcar_item_prodImage_img)
        ImageView shopcarItemProdImageImg;
        @InjectView(R.id.shopcar_item_prodName_text)
        TextView shopcarItemProdNameText;
        @InjectView(R.id.shopcar_item_prodCount_text)
        TextView shopcarItemProdCountText;
        @InjectView(R.id.shopcar_item_prodSize_text)
        TextView shopcarItemProdSizeText;
        @InjectView(R.id.shopcar_item_prodColor_text)
        TextView shopcarItemProdColorText;
        @InjectView(R.id.shopcar_item_subPrice_text)
        TextView shopcarItemSubPriceText;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
