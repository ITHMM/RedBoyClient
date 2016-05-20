package com.itheima.redboyclient.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.itheima.redboyclient.DB.MyDBOpenHelper;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class GoodsProvider extends ContentProvider {
    private static final String TAG = "GoodsProvider";
    public static final int SUCCESS = 1;
    /**
     * 创建一个匹配器,检查uri的规则,如果uri匹配失败 返回-1
     */
    static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI("com.itheima.redboyclient", "goods", SUCCESS);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.i(TAG, "query 查询数据");
            MyDBOpenHelper helper = new MyDBOpenHelper(getContext());
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("goods", projection, selection, selectionArgs,
                    null, null, sortOrder);
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.i(TAG, "添加了数据");
            MyDBOpenHelper helper = new MyDBOpenHelper(getContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            db.insert("goods", null, values);
            // 利用内容提供者的解析器,通知内容观察者数据发生了变化
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.i(TAG, "delete 删除数据");
            MyDBOpenHelper helper = new MyDBOpenHelper(getContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            int deleteCount = db.delete("goods", selection, selectionArgs);
            // 利用内容提供者的解析器,通知内容观察者数据发生了变化
            getContext().getContentResolver().notifyChange(uri, null);
            return deleteCount;
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS) {
            Log.i(TAG, "update 更新数据");
            MyDBOpenHelper helper = new MyDBOpenHelper(getContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            int updateCount = db.update("goods", values, selection, selectionArgs);
            // 利用内容提供者的解析器,通知内容观察者数据发生了变化
            getContext().getContentResolver().notifyChange(uri, null);
            return updateCount;
        } else {
            throw new IllegalArgumentException("口令 不正确");
        }
    }

}
