package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/4/3.
 * 留言
 */
public class RemarkFragment extends BaseFragment {
    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.head_save_text)
    TextView headSaveText;
    @InjectView(R.id.remark_edit)
    EditText remarkEdit;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.order_remark_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick({R.id.head_back_text, R.id.head_save_text})
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        switch (view.getId()) {
            case R.id.head_back_text: //返回

                fm.popBackStack();
                break;
            case R.id.head_save_text: //保存
                PayCenterFragment.remarkView_text.setText(remarkEdit.getText().toString().trim());

                fm.popBackStack();
                break;
        }
    }
}
