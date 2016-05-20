package com.itheima.redboyclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by xiaoyan on 2016/4/3.
 */
public class SendTimeFragment extends BaseFragment {
    @InjectView(R.id.head_save_text)
    TextView headSaveText;
    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.send_allday_img)
    ImageView sendAlldayImg;
    @InjectView(R.id.send_allday_rel)
    RelativeLayout sendAlldayRel;
    @InjectView(R.id.send_holiday_img)
    ImageView sendHolidayImg;
    @InjectView(R.id.send_holiday_rel)
    RelativeLayout sendHolidayRel;
    @InjectView(R.id.send_workday_img)
    ImageView sendWorkdayImg;
    @InjectView(R.id.send_workday_rel)
    RelativeLayout sendWorkdayRel;

    @Override
    public View CreateView() {
        View view = View.inflate(mActivity, R.layout.sendtime_activity, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        sendAlldayImg.setVisibility(View.INVISIBLE);
        sendHolidayImg.setVisibility(View.INVISIBLE);
        sendWorkdayImg.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick({R.id.head_back_text, R.id.send_allday_rel, R.id.send_holiday_rel, R.id.send_workday_rel,R.id.head_save_text})
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        switch (view.getId()) {
            case R.id.head_back_text:// 结束当前页面，返回上个页面
                PayCenterFragment.sendTimeValue_text.setText("");
                fm.popBackStack();
                break;
            case R.id.head_save_text:
                fm.popBackStack();
                break;
            case R.id.send_allday_rel: //工作日、双休日及假日均可送货
                sendAlldayImg.setVisibility(View.VISIBLE);
                sendHolidayImg.setVisibility(View.INVISIBLE);
                sendWorkdayImg.setVisibility(View.INVISIBLE);
                PayCenterFragment.sendTimeValue_text.setText("工作日、双休日及假日均可送货");
                break;
            case R.id.send_holiday_rel:
                sendAlldayImg.setVisibility(View.INVISIBLE);
                sendHolidayImg.setVisibility(View.VISIBLE);
                sendWorkdayImg.setVisibility(View.INVISIBLE);
                PayCenterFragment.sendTimeValue_text.setText("双休日、假日送货");
                break;
            case R.id.send_workday_rel:
                sendAlldayImg.setVisibility(View.INVISIBLE);
                sendHolidayImg.setVisibility(View.INVISIBLE);
                sendWorkdayImg.setVisibility(View.VISIBLE);
                PayCenterFragment.sendTimeValue_text.setText("只工作日送货");
                break;
        }
    }
}
