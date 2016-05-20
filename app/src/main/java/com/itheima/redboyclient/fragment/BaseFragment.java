package com.itheima.redboyclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.bean.AddresslistResponse;

/**
 * Created by ly on 2016/3/30.
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    public AddresslistResponse.AddresslistBean addresslistBean;
    protected BackHandledInterface mBackHandledInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandledInterface)) {
            throw new ClassCastException("主Activity必须 实现 BackHandledInterface");
        } else {
            this.mBackHandledInterface = (BackHandledInterface) getActivity();
        }
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = CreateView();
        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //告诉FragmentActivity，当前Fragment在栈顶
        mBackHandledInterface.setSelectedFragment(this);
    }

    public abstract View CreateView();

    public abstract void initData();

    public abstract boolean onBackPressed();

    public interface BackHandledInterface {
        public abstract void setSelectedFragment(BaseFragment selectedFragment);
    }

    //from  xiaowu
    public void skip(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.rl_home_fragment, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }
}
