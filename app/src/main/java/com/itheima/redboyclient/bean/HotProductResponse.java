package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/2.
 * 热门单品
 */
public class HotProductResponse extends RBResponse {

    /**
     * list_count : 10
     * productlist : [{"id":10000,"marketprice":1000,"name":"雅戈尔0","pic":"images/image10.png","price":800},{"id":10001,"marketprice":1001,"name":"雅戈尔1","pic":"images/image10.png","price":800},{"id":10002,"marketprice":1002,"name":"雅戈尔2","pic":"images/image10.png","price":800},{"id":10003,"marketprice":1003,"name":"雅戈尔3","pic":"images/image10.png","price":800},{"id":10004,"marketprice":1004,"name":"雅戈尔4","pic":"images/image10.png","price":800},{"id":10005,"marketprice":1005,"name":"雅戈尔5","pic":"images/image10.png","price":800},{"id":10006,"marketprice":1006,"name":"雅戈尔6","pic":"images/image10.png","price":800},{"id":10007,"marketprice":1007,"name":"雅戈尔7","pic":"images/image10.png","price":800},{"id":10008,"marketprice":1008,"name":"雅戈尔8","pic":"images/image10.png","price":800},{"id":10009,"marketprice":1009,"name":"雅戈尔9","pic":"images/image10.png","price":800}]
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
     * 商品详情集合
     */
    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ProductlistBean {
        private int id;
        /**
         * 市场价
         */
        private int marketprice;
        private String name;
        private String pic;
        /**
         * 会员价
         */
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        /**
         * 市场价
         */
        public int getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(int marketprice) {
            this.marketprice = marketprice;
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
        /**
         * 会员价
         */
        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
