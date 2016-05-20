package com.itheima.redboyclient.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.PromAdapter;
import com.itheima.redboyclient.bean.PromResponse;
import com.itheima.redboyclient.bean.RBResponse;
import com.itheima.redboyclient.net.HttpLoader;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

/**
 * Created by 李正春 on 2016/4/2.
 * Fragment弹框
 */
public class PromFragmentDialog extends DialogFragment implements HttpLoader.ResponseListener, AdapterView.OnItemClickListener {

    private static final String TAG = "PromFragmentDialog";
    private ListView mPromDialogLv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getContext(), R.layout.fragment_dialog_prom,null);
        mPromDialogLv = (ListView) view.findViewById(R.id.promDialogLv);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setPositiveButton("Sign in",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                            }
//                        }).setNegativeButton("Cancel", null);
//        return builder.create();
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpLoader.get(ConstantsRedBaby.URL_TOPIC, null, PromResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPIC, this, true);

    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        final PromResponse promResponse = (PromResponse) response;
        mPromDialogLv.setAdapter(new PromAdapter(promResponse, getContext(), getFragmentManager()));
        mPromDialogLv.setOnItemClickListener(this);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        PromResponse promResponse = new PromResponse();
//        List<PromResponse.TopicEntity> topics = promResponse.getTopic();
//        PromResponse.TopicEntity topicEntity = topics.get(position);//当前被点击条目的商品实体
        Log.i(TAG,"跳转商品详情界面");
    }
    //布局控件的点击事件不会在DialogFragment中调用,会在show()当前DialogFragment的Activity中调用
//    public void cancel(View view){
//        Log.i(TAG,"取消FragmentDialog");
//    }
}
