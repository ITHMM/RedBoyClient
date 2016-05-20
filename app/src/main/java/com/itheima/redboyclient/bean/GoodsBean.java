package com.itheima.redboyclient.bean;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class GoodsBean {
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品Id
     */
    private String goodId;
    /**
     * 单件商品数量
     */
    private String count;
    /**
     * 商品尺寸
     */
    private String size;
    /**
     * 商品颜色
     */
    private String color;
    /**
     * 商品名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 商品Id
     */
    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    /**
     * 单件商品数量
     */
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    /**
     * 商品尺寸
     */
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    /**
     * 商品颜色
     */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
