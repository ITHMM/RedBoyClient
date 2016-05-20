package com.itheima.redboyclient.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.DB.DBhelper;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.AddressAdapter;
import com.itheima.redboyclient.adapter.AddresslistAdapter;
import com.itheima.redboyclient.bean.AddresslistResponse;
import com.itheima.redboyclient.bean.Area;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/4/2.
 */
public class AddAddressFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "AddAddressFragment";
    private static final int SAVE = 0;
    private static final int CANCEL = 1;
    private RequestQueue queue;
    //收货人姓名
    @InjectView(R.id.add_address_name_edit)
    EditText addAddressNameEdit;
    //手机
    @InjectView(R.id.add_address_mobile_edit)
    EditText addAddressMobileEdit;
    //
    @InjectView(R.id.add_address_tel_edit)
    EditText addAddressTelEdit;
    //省
    @InjectView(R.id.add_address_province_name_text)
    TextView addAddressProvinceNameText;
    //城市
    @InjectView(R.id.add_address_city_name_text)
    TextView addAddressCityNameText;
    //地区
    @InjectView(R.id.add_address_area_name_text)
    TextView addAddressAreaNameText;
    //详细地址
    @InjectView(R.id.add_address_detail_edit)
    EditText addAddressDetailEdit;
    @InjectView(R.id.add_address_zipcode_edit)
    EditText addAddressZipcodeEdit;
    @InjectView(R.id.save_address_button)
    Button saveAddressButton;
    @InjectView(R.id.cancel_address_button)
    Button cancelAddressButton;
    @InjectView(R.id.shopcar_body_srcoll)
    ScrollView shopcarBodySrcoll;
    //是否选择省级列表
    private boolean isShowProvince = false;
    //是否选择市级列表
    private boolean isShowCity = false;
    //是否选择地区列表
    private boolean isShowArea = false;

    public static enum Status{
        Province,City,District
    }
    private Status status = Status.Province;

    // 地址列表信息
    public AddresslistResponse updateAddressDate;

    public AddresslistResponse.AddresslistBean addresslistBean = new AddresslistResponse.AddresslistBean();
    private ArrayList<Area> province;
    private String provinceCode;
    private String cityCode;
    private DBhelper dBhelper;
    private ListView mListView;
    private PopupWindow pw;
    private PopupWindow pcity;

    private AddressAdapter mAdapter;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.add_address_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        addAddressNameEdit.setText("张三");
        addAddressMobileEdit.setText("13112345678");
        addAddressTelEdit.setText("67123456");
        addAddressDetailEdit.setText("辰泰科技园302");
        addAddressZipcodeEdit.setText("410102");

        saveAddressButton.setOnClickListener(this);
        saveAddressButton.setTag(SAVE);
        cancelAddressButton.setOnClickListener(this);
        cancelAddressButton.setTag(CANCEL);

        // 初始化ListView控件和里边的数据
        dBhelper = new DBhelper(mActivity);

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


    @OnClick({R.id.add_address_province_name_text,R.id.add_address_city_name_text,R.id.add_address_area_name_text})
    public void show(View view) {
        switch (view.getId()) {
            case R.id.add_address_province_name_text:  //省
                status = Status.Province;
                province =  dBhelper.getProvince();
                mListView = initListView(province);
                showPopupWindow(addAddressProvinceNameText);
                addAddressCityNameText.setText("请选择");
                addAddressAreaNameText.setText("请选择");
                break;
            case R.id.add_address_city_name_text:  //市
                //如果选择了省可点击
                if(isShowProvince){

                    status = Status.City;
                    province.clear();
                    province = dBhelper.getCity(provinceCode);
                    mAdapter.notifyDataSetChanged();
                    mListView = initListView(province);
                    showPopupWindow(addAddressCityNameText);
                    addAddressAreaNameText.setText("请选择");
                }else {
                    Toast.makeText(mActivity,"请选择省份",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.add_address_area_name_text:  //区
                if(isShowCity){
                    status = Status.District;
                    province.clear();
                    province = dBhelper.getDistrict(cityCode);
                    mAdapter.notifyDataSetChanged();
                    mListView = initListView(province);
                    showPopupWindow(addAddressDetailEdit);
                }else {
                    Toast.makeText(mActivity,"请选择城市",Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    private void showPopupWindow(TextView view) {
        if(pw==null){

            pw = new PopupWindow(mListView,view.getWidth()+2, 500);
            // 设置可以使用焦点
            pw.setFocusable(true);
            // 设置popupwindow点击外部可以被关闭
            pw.setOutsideTouchable(true);
            // 设置一个popupWindow的背景
            pw.setBackgroundDrawable(new BitmapDrawable());
            // 把popupwindow显示出来, 显示的位置是: 在输入框的下面, 和输入框是连着的.
            pw.showAsDropDown(view,0, -5);
        } else if (pw != null&&pw.isShowing()) {
            pw.dismiss();
        }else {
            pw = new PopupWindow(mListView,view.getWidth()+2, 500);
            // 设置可以使用焦点
            pw.setFocusable(true);
            // 设置popupwindow点击外部可以被关闭
            pw.setOutsideTouchable(true);
            // 设置一个popupWindow的背景
            pw.setBackgroundDrawable(new BitmapDrawable());
            // 把popupwindow显示出来, 显示的位置是: 在输入框的下面, 和输入框是连着的.
            pw.showAsDropDown(view, 0, -5);
        }
    }

    private ListView initListView(ArrayList<Area> province) {

        ListView mListView = new ListView(mActivity);
        mListView.setDividerHeight(0);
        // 去掉右侧垂直滑动条
        mListView.setVerticalScrollBarEnabled(false);
        //设置背景
        mListView.setBackgroundResource(R.drawable.listview_background);
        mListView.setOnItemClickListener(this);
        // 设置适配器展示数据
        mAdapter = new AddressAdapter(mActivity, province);
        mListView.setAdapter(mAdapter);
        return mListView;
    }


    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        RelativeLayout rl = (RelativeLayout) getActivity().findViewById(R.id.rl_home_fragment);
        switch ((int) v.getTag()) {
            case 0:         //保存
                if(!isShowProvince){
                    Toast.makeText(mActivity,"请选择省份",Toast.LENGTH_SHORT).show();
                    return;
                }else if (!isShowCity){
                    Toast.makeText(mActivity,"请选择城市",Toast.LENGTH_SHORT).show();
                    return;
                }else if (!isShowArea){
                    Toast.makeText(mActivity,"请选择地区",Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = addAddressNameEdit.getText().toString().trim();
                String mobile = addAddressMobileEdit.getText().toString().trim();
                String Address =
                        addAddressProvinceNameText.getText().toString().trim() + addAddressCityNameText.getText().toString().trim() +
                        addAddressAreaNameText.getText().toString().trim()+ addAddressDetailEdit.getText().toString().trim();
                addresslistBean.setName(name);
                addresslistBean.setPhonenumber(mobile);
                addresslistBean.setAreadetail(Address);
                Toast.makeText(mActivity, "保存成功", Toast.LENGTH_SHORT).show();
                App.application.addresslistResponse.getAddresslist().add(addresslistBean);
               // AddresslistFrament frament =new AddresslistFrament();
               // frament.adapter.notifyDataSetChanged();

                manager.popBackStack();
                break;
            case 1:         //取消,退出到上一级
                manager.popBackStack();
                break;
        }
    }
    //地址条目点击事件,获取省对应城市数据
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (status){
            case Province:
                addAddressProvinceNameText.setText(province.get(position).getName());
                provinceCode = province.get(position).getCode();
                isShowProvince = true;
                break;
            case City:
                addAddressCityNameText.setText(province.get(position).getName());
                cityCode = province.get(position).getCode();
                isShowCity =true;
                break;
            case District:
                addAddressAreaNameText.setText(province.get(position).getName());
                isShowArea =true;
                break;
        }

        mAdapter.notifyDataSetChanged();
        pw.dismiss();

    }
}
