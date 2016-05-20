package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class ProductDetailResponse extends RBResponse {

    /**
     * 单件商品类
     */
    private ProductBean product;

    /**
     * 单件商品类
     */
    public ProductBean getProduct() {
        return product;
    }

    /**
     * 单间商品类
     */
    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public static class ProductBean {
        /**
         * 是否可售
         */
        private String available;
        /**
         * 单件商品购买上限
         */
        private int buyLimit;
        /**
         * 商品评论数
         */
        private int comment_count;
        /**
         * 商品Id
         */
        private int id;
        /**
         * 配货说明,可发送至
         */
        private String inventory_area;
        /**
         * 剩余时间
         */
        private int leftTime;
        /**
         * 限时抢购价
         */
        private int limitPrice;
        /**
         * 市场价
         */
        private int marketprice;
        /**
         * 商品名称
         */
        private String name;
        /**
         * 会员价
         */
        private int price;
        /**
         * 评分
         */
        private String score;
        /**
         * 商品大图的路径集合
         */
        private List<String> bigPic;
        /**
         * 商品图片路径的集合
         */
        private List<String> pic;
        /**
         * 相关促销信息展示的集合
         */
        private List<String> product_prom;

        /**
         * 是否可售
         */
        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        /**
         * 单件商品购买上限
         */
        public int getBuyLimit() {
            return buyLimit;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        /**
         * 商品评论数
         */
        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        /**
         * 商品Id
         */
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        /**
         * 配货说明,可发送至
         */
        public String getInventory_area() {
            return inventory_area;
        }

        public void setInventory_area(String inventory_area) {
            this.inventory_area = inventory_area;
        }

        /**
         * 剩余时间
         */
        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        /**
         * 限时抢购价
         */
        public int getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
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
         * 会员价
         */
        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        /**
         * 评分
         */
        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        /**
         * 商品大图的路径集合
         */
        public List<String> getBigPic() {
            return bigPic;
        }

        public void setBigPic(List<String> bigPic) {
            this.bigPic = bigPic;
        }

        /**
         * 商品图片路径的集合
         */
        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }

        /**
         * 相关促销信息展示的集合
         */
        public List<String> getProduct_prom() {
            return product_prom;
        }

        public void setProduct_prom(List<String> product_prom) {
            this.product_prom = product_prom;
        }
    }
}
