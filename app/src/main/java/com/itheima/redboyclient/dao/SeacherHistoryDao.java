package com.itheima.redboyclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.redboyclient.DB.SeacherHistoryHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xch on 2016/4/5.
 * 搜索历史的数据库操作对象
 */
public class SeacherHistoryDao {

    private final SeacherHistoryHelper mHelper;

    public SeacherHistoryDao(Context context){
        mHelper = new SeacherHistoryHelper(context);
    }

    /**
     * 添加一条搜索历史记录的方法
     * @param seacherHistory 搜索的关键字
     */
    public void addSeacherHistory(String seacherHistory){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues valuse = new ContentValues();
        valuse.put("seacher",seacherHistory);
        db.insert("seacher_history", null, valuse);
        db.close();
    }

    /**
     * 查询所有的历史记录
     * @return
     */
    public List<String> findAll(){
        List<String> seacher_history = new ArrayList<String>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query("seacher_history", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            seacher_history.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return seacher_history;
    }
}
