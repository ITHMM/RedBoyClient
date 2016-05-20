package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/2.
 * 商品专题
 */
public class TopicPlistResponse extends RBResponse {

    /**
     * list_count : 2
     * productlist : [{"comment_count":30,"id":101,"marketprice":1000.12,"name":"奶粉0","pic":"images/product_05.jpg","price":800.12},{"comment_count":31,"id":101,"marketprice":1000.12,"name":"奶粉1","pic":"images/product_05.jpg","price":800.12}]
     */

    private int list_count;
    /**
     * 商品集合
     */

    private List<ProductlistBean> productlist;

    public int getList_count() {
        return list_count;
    }

    public void setList_count(int list_count) {
        this.list_count = list_count;
    }
    /**
     * 商品集合
     */
    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ProductlistBean {
        private int comment_count;
        private int id;
        private double marketprice;
        private String name;
        private String pic;
        private double price;

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(double marketprice) {
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
