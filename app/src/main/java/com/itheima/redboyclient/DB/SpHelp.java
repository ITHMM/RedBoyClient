package com.itheima.redboyclient.DB;

import android.content.SharedPreferences;

import com.itheima.redboyclient.App;

/**
 * Sp的帮助类
 */
public class SpHelp {
    private SharedPreferences sp = App.application.SP;

    public void setsp(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setsp(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public boolean getsp(String key, boolean value) {

        Boolean result = sp.getBoolean(key, value);
        return result;
    }

    public String getsp(String key, String value) {

        String result = sp.getString(key, value);
        return result;
    }


}
