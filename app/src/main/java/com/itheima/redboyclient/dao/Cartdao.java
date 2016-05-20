package com.itheima.redboyclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.redboyclient.bean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * 黑名单数据库,增删改查的api
 * Created by ly on 2016/4/2.
 */
public class Cartdao {
    private CartHelper mHelper;

    /**
     * 在构造方法里面初始化helper对象
     *
     * @param context
     */
    public Cartdao(Context context) {
        mHelper = new CartHelper(context);
    }

    /**
     * 添加一件商品
     *
     * @param name  商品名称
     * @param color 商品颜色
     * @param pic   商品图片
     * @param size  商品尺寸
     * @param price 商品价格
     */
    public void addProd(String name, String color, String pic, String size, String price) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("color", color);
        values.put("size", size);
        values.put("pic", pic);
        values.put("price", price);
        db.insert("carts", null, values);
        db.close();
    }

    /**
     * 删除购物车的商品
     *
     * @param name 商品名称
     * @return
     */
    public boolean deleteProd(String name) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int carts = db.delete("carts", "name = ?", new String[]{name});
        db.close();
        if (carts > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateProd(String name, String newColor, String newPic, String newSize, String newPrice) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("color", newColor);
        values.put("pic", newPic);
        values.put("size", newSize);
        values.put("price", newPrice);
        int update = db.update("carts", values, "name=?", new String[]{name});
        db.close();
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询商品的价格
     *
     * @param name
     * @return
     */
    public String findProd(String name) {
        String price = null;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query("carts", new String[]{"price"}, "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            price = cursor.getString(0);
        }
        return price;
    }

    public List<Product> findAll() {
        List<Product> products = null;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.query("carts", null, null, null, null, null, null);
            products = new ArrayList<>();
            while (cursor.moveToNext()) {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex("name")));
                product.setColor(cursor.getString(cursor.getColumnIndex("color")));
                product.setPic(cursor.getString(cursor.getColumnIndex("pic")));
                product.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                product.setSize(cursor.getString(cursor.getColumnIndex("size")));
                products.add(product);
            }
            cursor.close();
            db.close();
        }
        return products;
    }
}
