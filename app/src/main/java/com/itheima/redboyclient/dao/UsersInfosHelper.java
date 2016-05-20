package com.itheima.redboyclient.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 用户信息的数据 库
 */
public class UsersInfosHelper extends SQLiteOpenHelper {
    public static final String DB = "userinfos.db";
    public String sql = "create table userinfos(_id integer primary key autoincrement,username text,password text)";

    public UsersInfosHelper(Context context) {
        super(context, DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
