package com.itheima.redboyclient.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xch on 2016/4/5.
 * 搜索历史的数据库
 */
public class SeacherHistoryHelper extends SQLiteOpenHelper {
    // 创建数据库
    public SeacherHistoryHelper(Context context) {
        super(context, "seacher.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table seacher_history (_id integer primary key autoincrement,seacher varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
