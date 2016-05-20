package com.itheima.redboyclient.bean;


import java.util.List;

/**
 * Created by Administrator on 2016/4/1/001.
 */
public class FavoriteResponse extends RBResponse {
    private List<FavoriteBean> productlist;
    private int list_coun;

    public int getList_coun() {
        return list_coun;
    }

    public void setList_coun(int list_coun) {
        this.list_coun = list_coun;
    }

    public List<FavoriteBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<FavoriteBean> productlist) {
        this.productlist = productlist;
    }

    /**
     *  "id": "1102539",        //商品ID
        "name": "雅培金装",    //商品名称
        "pic": "",              //商品图片URL
        "marketprice": "79",     //市场价
        "price": "78",          //会员价
     */
    public static class FavoriteBean {
        /**
         * 商品Id
         */
        private int id;
        /**
         * 商品名称
         */
        private String name;
        /**
         * 商品图片URL
         */
        private String pic;
        /**
         * 市场价
         */
        private String marketprice;
        /**
         * 会员价
         */
        private String price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(String marketprice) {
            this.marketprice = marketprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
