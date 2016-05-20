package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/1.
 * 限时抢购JavaBean
 */
public class LimitBuyResponse extends RBResponse {

    /**
     * list_count : 10
     * productlist : [{"id":102,"lefttime":1335189632954,"limitprice":800,"name":"雅培金装0","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335189732954,"limitprice":800,"name":"雅培金装1","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335189832954,"limitprice":800,"name":"雅培金装2","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335189932954,"limitprice":800,"name":"雅培金装3","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335190032954,"limitprice":800,"name":"雅培金装4","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335190132954,"limitprice":800,"name":"雅培金装5","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335190232954,"limitprice":800,"name":"雅培金装6","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335190332954,"limitprice":800,"name":"雅培金装7","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335190432954,"limitprice":800,"name":"雅培金装8","pic":"images/image10.png","price":1000},{"id":102,"lefttime":1335190532954,"limitprice":800,"name":"雅培金装9","pic":"images/image10.png","price":1000}]
     */

    private int list_count;
    /**
     * 商品详情集合
     */

    private List<ProductlistBean> productlist;

    public int getList_count() {
        return list_count;
    }

    public void setList_count(int list_count) {
        this.list_count = list_count;
    }
    /**
     * 获取商品详情集合
     */
    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    /**
     * 商品详情类
     */
    public static class ProductlistBean {
        /**
         * 商品id
         */
        private int id;
        /**
         * 商品剩余时间,单位为秒
         */
        private long lefttime;
        /**
         * 商品特价
         */
        private int limitprice;
        /**
         * 商品名字
         */
        private String name;
        /**
         * 商品图片
         */
        private String pic;
        /**
         * 商品价格
         */
        private int price;
        /**
         * 商品id
         */
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        /**
         * 商品剩余时间,单位为秒
         */
        public long getLefttime() {
            return lefttime;
        }

        public void setLefttime(long lefttime) {
            this.lefttime = lefttime;
        }
        /**
         * 商品特价
         */
        public int getLimitprice() {
            return limitprice;
        }

        public void setLimitprice(int limitprice) {
            this.limitprice = limitprice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
