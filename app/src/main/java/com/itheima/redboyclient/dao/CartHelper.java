package com.itheima.redboyclient.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ly on 2016/4/2.
 */
public class CartHelper extends SQLiteOpenHelper {
    public static final String DB = "cart.db";
    public String sql = "create table carts(_id integer primary key autoincrement,name text,pic text,color text,size text,price text)";

    public CartHelper(Context context) {
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
