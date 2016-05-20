package com.itheima.redboyclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 用户信息数据库,增删改查的api
 */
public class UsersInfosDao {
    private UsersInfosHelper mHelper;

    /**
     * 在构造方法里面初始化helper对象
     *
     * @param context
     */
    public UsersInfosDao(Context context) {
        mHelper = new UsersInfosHelper(context);
    }

    /**
     * 增加一个用户
     *
     */
    public void addUser(String username, String password) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        db.insert("userinfos", null, values);
        db.close();
    }



    /**
     * 查询用户信息
     * @param username
     * @return
     */
    public String findUserinfo(String username) {
        String password = null;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query("userinfos", new String[]{"password"}, "username=?", new String[]{username}, null, null, null);
        while (cursor.moveToNext()) {
            password = cursor.getString(0);
        }
        return password;
    }
}
