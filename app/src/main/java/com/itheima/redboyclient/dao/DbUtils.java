package com.itheima.redboyclient.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.itheima.redboyclient.bean.GoodsBean;

import org.seny.android.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class DbUtils {
    private static final String TAG = "DbUtils";
    private Context mContext;
    /**
     * 内容解析器
     */
    private final ContentResolver mResolver;
    /**
     * 商品Uri
     */
    private final Uri mUri;

    public DbUtils(Context context) {
        this.mContext = context;
        // 得到内容提供者的解析器
        mResolver = mContext.getContentResolver();
        mUri = Uri.parse("content://com.itheima.redboyclient/goods");
    }

    /**
     * 增加
     *
     * @param name   商品名称
     * @param goodId 商品Id
     * @param count  单件商品数量
     * @param size   商品尺寸
     * @param color  商品颜色
     * @return insertUrl新插入条目的Uri
     */
    public Uri insert(String name, String goodId, String count, String size, String color) {
        List<GoodsBean> goodsBeans = query(goodId);
        if (goodsBeans.size() == 0) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("goodId", goodId);
            values.put("count", count);
            values.put("size", size);
            values.put("color", color);
            //通过内容解析器让内容提供者添加一条数据
            Uri insertUrl = mResolver.insert(mUri, values);
            return insertUrl;
        } else {
            GoodsBean goodsBean = goodsBeans.get(0);
            String goodsCount = goodsBean.getCount();
            int i = 0;
            try {
                i = Integer.parseInt(goodsCount) + 1;

            } catch (Exception e) {
                MyToast.show(mContext, "商品数量必须是数据类型");
            }
            int update = update(goodId, String.valueOf(i));
            return null;
        }

    }

    /**
     * 删
     *
     * @param id 商品Id
     * @return deleteCount商品被删除条数
     */
    public int delete(String id) {
        int deleteCount = mResolver.delete(mUri, "goodId=?", new String[]{id});
        Log.i(TAG, "删除" + deleteCount + "个条目");
        return deleteCount;
    }

    /**
     * 删除所有
     *
     * @return deleteCount商品被删除条数
     */
    public int deleteAAll() {
        int deleteCount = mResolver.delete(mUri, null, null);
        Log.i(TAG, "删除" + deleteCount + "个条目");
        return deleteCount;
    }

    /**
     * 改
     *
     * @param Id       商品Id
     * @param goodMsgs 依次写入单件商品数量:count,商品尺寸:size,商品颜色:color
     * @return updateCount商品更新条数
     */
    public int update(String Id, String... goodMsgs) {
        ContentValues values = new ContentValues();
        if (goodMsgs.length == 0) {
            return 0;
        } else if (goodMsgs.length == 1) {
            values.put("count", goodMsgs[0]);

        } else if (goodMsgs.length == 2) {
            values.put("count", goodMsgs[0]);
            values.put("size", goodMsgs[1]);
        } else {
            values.put("count", goodMsgs[0]);
            values.put("size", goodMsgs[1]);
            values.put("color", goodMsgs[2]);
        }
        int updateCount = mResolver.update(mUri, values, "goodId=?", new String[]{Id});
        Log.i(TAG, "更新" + updateCount + "个条目");
        return updateCount;
    }

    /**
     * 查
     *
     * @return goods所有商品的集合
     */
    public List<GoodsBean> queryAll() {
        Cursor cursor = mResolver.query(mUri, new String[]{"name", "goodId", "count", "size", "color"}, null, null, null);
        List<GoodsBean> goods = new ArrayList<GoodsBean>();
        GoodsBean goodsBean;
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String goodId = cursor.getString(1);
            String count = cursor.getString(2);
            String size = cursor.getString(3);
            String color = cursor.getString(4);
            Log.i(TAG, "商品名称:" + name + "商品Id:" + goodId + "商品数量:" + count + "商品尺寸:" + size + "商品颜色:" + color);
            goodsBean = new GoodsBean();
            goodsBean.setName(name);
            goodsBean.setGoodId(goodId);
            goodsBean.setCount(count);
            goodsBean.setSize(size);
            goodsBean.setColor(color);
            goods.add(goodsBean);
        }
        cursor.close();
        return goods;
    }

    /**
     * 查
     *
     * @param Id 商品
     * @return goods所有商品的集合
     */
    public List<GoodsBean> query(String Id) {
        Cursor cursor = mResolver.query(mUri, new String[]{"name", "count", "size", "color"}, "goodId=?", new String[]{Id}, null);
        List<GoodsBean> goods = new ArrayList<GoodsBean>();
        GoodsBean goodsBean;
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String count = cursor.getString(1);
            String size = cursor.getString(2);
            String color = cursor.getString(3);
            Log.i(TAG, "商品名称:" + name + "商品数量:" + count + "商品尺寸:" + size + "商品颜色:" + color);
            goodsBean = new GoodsBean();
            goodsBean.setName(name);
            goodsBean.setCount(count);
            goodsBean.setSize(size);
            goodsBean.setColor(color);
            goods.add(goodsBean);
        }
        cursor.close();
        return goods;
    }
}
