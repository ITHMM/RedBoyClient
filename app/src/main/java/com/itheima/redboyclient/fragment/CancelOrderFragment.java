package com.itheima.redboyclient.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/4/5.
 * 取消订单对话框
 */
public class CancelOrderFragment extends DialogFragment {
    @InjectView(R.id.btnOK)
    Button btnOK;
    @InjectView(R.id.btnCancel)
    Button btnCancel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_login_dialog, null);
        ButterKnife.inject(this, view);
        builder.setView(view);

        return builder.create();
    }

    @OnClick({R.id.btnOK, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                dismiss();
                getFragmentManager().popBackStack();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
