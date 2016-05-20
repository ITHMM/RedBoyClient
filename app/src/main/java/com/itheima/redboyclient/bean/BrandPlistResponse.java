package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/2.
 * 品牌商品列表
 */
public class BrandPlistResponse extends RBResponse {

    /**
     * list_count : 10
     * productlist : [{"id":10000,"marketprice":1000,"name":"毛衣0","pic":"images/0.png","price":800},{"id":10001,"marketprice":1001,"name":"毛衣1","pic":"images/1.png","price":800},{"id":10002,"marketprice":1002,"name":"毛衣2","pic":"images/2.png","price":800},{"id":10003,"marketprice":1003,"name":"毛衣3","pic":"images/3.png","price":800},{"id":10004,"marketprice":1004,"name":"毛衣4","pic":"images/4.png","price":800},{"id":10005,"marketprice":1005,"name":"毛衣5","pic":"images/5.png","price":800},{"id":10006,"marketprice":1006,"name":"毛衣6","pic":"images/6.png","price":800},{"id":10007,"marketprice":1007,"name":"毛衣7","pic":"images/7.png","price":800},{"id":10008,"marketprice":1008,"name":"毛衣8","pic":"images/8.png","price":800},{"id":10009,"marketprice":1009,"name":"毛衣9","pic":"images/9.png","price":800}]
     */

    private int list_count;
    /**
     * id : 10000
     * marketprice : 1000
     * name : 毛衣0
     * pic : images/0.png
     * price : 800
     */

    private List<ProductlistBean> productlist;

    public int getList_count() {
        return list_count;
    }

    public void setList_count(int list_count) {
        this.list_count = list_count;
    }

    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ProductlistBean {
        private int id;
        private int marketprice;
        private String name;
        private String pic;
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
