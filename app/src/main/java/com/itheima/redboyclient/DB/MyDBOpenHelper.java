package com.itheima.redboyclient.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context) {
        //(上下文,数据库表名,游标工厂,版本号)
        super(context, "redboy.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table goods (_id integer primary key autoincrement," +
                "name varchar(20)," +//商品名称
                "goodId varchar(20)," +//商品Id
                "count varchar(20)," +//单件商品数量
                "size varchar(20)," +//商品尺寸
                "color varchar(20))");//商品颜色
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
