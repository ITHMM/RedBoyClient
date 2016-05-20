package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/2.
 * 新品上架
 */
public class NewProductResponse extends RBResponse {

    /**
     * list_count : 10
     * productlist : [{"id":10000,"marketprice":1000,"name":"雅培金装0","pic":"images/image10.png","price":800},{"id":10001,"marketprice":1001,"name":"雅培金装1","pic":"images/image10.png","price":800},{"id":10002,"marketprice":1002,"name":"雅培金装2","pic":"images/image10.png","price":800},{"id":10003,"marketprice":1003,"name":"雅培金装3","pic":"images/image10.png","price":800},{"id":10004,"marketprice":1004,"name":"雅培金装4","pic":"images/image10.png","price":800},{"id":10005,"marketprice":1005,"name":"雅培金装5","pic":"images/image10.png","price":800},{"id":10006,"marketprice":1006,"name":"雅培金装6","pic":"images/image10.png","price":800},{"id":10007,"marketprice":1007,"name":"雅培金装7","pic":"images/image10.png","price":800},{"id":10008,"marketprice":1008,"name":"雅培金装8","pic":"images/image10.png","price":800},{"id":10009,"marketprice":1009,"name":"雅培金装9","pic":"images/image10.png","price":800}]
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
