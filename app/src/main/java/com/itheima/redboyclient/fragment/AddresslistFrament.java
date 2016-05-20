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

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.AddresslistAdapter;
import com.itheima.redboyclient.bean.AddresslistResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/4/1.
 */
public class AddresslistFrament extends BaseFragment implements HttpLoader.ResponseListener {

    @InjectView(R.id.address_manage_list)
    ListView addressManageList;

    public  AddresslistAdapter adapter;
    //返回上个界面
    @InjectView(R.id.head_back_text)
    TextView headBackText;
    //添加新地址
    @InjectView(R.id.address_manager_add_text)
    TextView addressManagerAddText;
    //地址列表javabean
    private AddresslistResponse addresslistResponse;

    @Override
    public View CreateView() {

        View view = View.inflate(mActivity, R.layout.address_manage_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

        HttpLoader.get(ConstantsRedBaby.URL_ADDRESSLIST, null, AddresslistResponse.class, ConstantsRedBaby.REQUEST_CODE_ADDRESSLIST, this);

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick({R.id.head_back_text, R.id.address_manager_add_text})
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.head_back_text:
                //结束当前Fragment，返回上个Fragment
                fm.popBackStack();
                break;
            case R.id.address_manager_add_text:
                //新增收货地址
                ft.add(R.id.rl_home_fragment,new AddAddressFragment());
                ft.addToBackStack("AddAddressFragment");
                ft.hide(this);
                ft.commit();
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_ADDRESSLIST:
                if(App.application.addresslistResponse==null){

                    addresslistResponse  = (AddresslistResponse) response;
                }else {
                    addresslistResponse  =   App.application.addresslistResponse;
                }
                if (adapter == null) {
                    adapter = new AddresslistAdapter(mActivity, addresslistResponse);
                    addressManageList.setAdapter(adapter);
                    //长按
                    addressManageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            addresslistBean = App.application.addresslistResponse.getAddresslist().get(position);
                            if(PayCenterFragment.username!=null){
                                PayCenterFragment.username.setText("收货人：" + addresslistBean.getName());
                                PayCenterFragment.phone.setText("电话：" + addresslistBean.getPhonenumber());
                                PayCenterFragment.address.setText("地址：" + addresslistBean.getAreadetail());

                                FragmentManager fm = getFragmentManager();
                                fm.popBackStack();

                            }

                        }
                    });
                }
                break;

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
