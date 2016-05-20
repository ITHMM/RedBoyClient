package com.itheima.redboyclient;

import android.app.Application;
import android.content.SharedPreferences;

import com.itheima.redboyclient.bean.AddresslistResponse;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p/>
 * Created by Seny on 2015/12/1.
 */
public class App extends Application {

    /**
     * 全局Context，方便引用
     */
    public static App application;
    /**
     * 初始化SP&EDIT
     */
    public SharedPreferences SP;
    public SharedPreferences.Editor EDIT;
    public AddresslistResponse addresslistResponse;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        //初始化通用的SP&EDIT
        SP = getSharedPreferences("config", MODE_PRIVATE);
        EDIT = SP.edit();
    }

}
