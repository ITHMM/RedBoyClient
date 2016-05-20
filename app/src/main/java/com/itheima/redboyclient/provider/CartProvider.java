package com.itheima.redboyclient.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import com.itheima.redboyclient.dao.CartHelper;

/**
 * 操作购物车crud方法的provider
 * Created by ly on 2016/4/2.
 */
public class CartProvider extends ContentProvider {
    private static final String TAG = "CartProvider";
    private SQLiteDatabase db;
    private CartHelper mHelper;
    private static final String TABLE = "carts";
    private static final String authority = "com.itheima.redboyclient.provider.CartProvider";
    public static final Uri CARTS_URI = Uri.parse("content://" + authority + "/carts");

    /**
     * 定义静态常量,用于规范格式,防止拼写错误
     * BaseColumns这个接口提供了自动层长的ID和COUNT这两个字段
     * 用于provider对数据库的操作,
     */
    public static class CARTS implements BaseColumns {
        public static final String PRODNAME = "prodname";
        public static final String COLOR = "color";
        public static final String SIZE = "size";
        public static final String PRICE = "price";
        public static final String SUM = "sum";
    }

    @Override
    public boolean onCreate() {
        mHelper = new CartHelper(getContext());
        return mHelper == null ? false : true;
    }

    private static UriMatcher matcher;
    //地址匹配码,用以简洁同一数据源时查找不同表的情况
    private static final int CART_CODE = 0;

    //地址匹配的静态代码块
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(authority, TABLE, CART_CODE);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case 0:
                Log.i(TAG, "insert: ");
                db = mHelper.getWritableDatabase();
                if (values == null) {
                    values = new ContentValues();
                    values.put(CartProvider.CARTS.PRODNAME, "雅培金装");
                    values.put(CartProvider.CARTS.COLOR, "红色");
                    values.put(CartProvider.CARTS.PRICE, "89");
                    values.put(CartProvider.CARTS.SIZE, "M");
                    values.put(CartProvider.CARTS.SUM, "89");
                }
                long insert = db.insert(TABLE, "", values);

                if (insert > 0) {
                    uri = ContentUris.withAppendedId(uri, insert);
                    //null为所有观察者
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                break;
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = mHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case 0:
                long _id = ContentUris.parseId(uri);
                selection = "_id = ?";
                selectionArgs = new String[]{String.valueOf(_id)};
                int delete = db.delete(TABLE, selection, selectionArgs);
                if(delete > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);//null所有的观察者
                }
                break;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


}
