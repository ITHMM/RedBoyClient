package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.AddresslistResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaoyan on 2016/4/1.
 */
public class AddresslistAdapter extends BaseAdapter {

    private Context context;
   // public static AddresslistResponse addressDate;

    public AddresslistAdapter(Context context, AddresslistResponse addressDate) {
        this.context = context;
        App.application.addresslistResponse = addressDate;
    }

    @Override
    public int getCount() {
        return App.application.addresslistResponse.getAddresslist().size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.address_manage_listitem, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder =  (ViewHolder) convertView.getTag();
        }
       // if(position==0){
           // holder.addressListitemReceiverText.setText("位敏");
           // holder.addressListitemPhoneText.setText("186XXXX0318");
           // holder.addressListitemAdsText.setText("北京市朝阳区东三环39号建外soho17号楼802");
       // }else if (position==1){
          //  holder.addressListitemReceiverText.setText("小颜");
          //  holder.addressListitemPhoneText.setText("133XXXX3333");
           // holder.addressListitemAdsText.setText("长沙高新区旺龙路56号辰泰科技园2号楼3层");
       // }else {
            holder.addressListitemReceiverText.setText(App.application.addresslistResponse.getAddresslist().get(position).getName());
            holder.addressListitemPhoneText.setText(App.application.addresslistResponse.getAddresslist().get(position).getPhonenumber());
            holder.addressListitemAdsText.setText(App.application.addresslistResponse.getAddresslist().get(position).getAreadetail());
      //  }


        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    static class ViewHolder {

        @InjectView(R.id.address_listitem_receiver_text)
        TextView addressListitemReceiverText;
        @InjectView(R.id.address_listitem_phone_text)
        TextView addressListitemPhoneText;
        @InjectView(R.id.address_listitem_ads_text)
        TextView addressListitemAdsText;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
