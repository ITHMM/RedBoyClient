package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by ZCM on 2016/4/3.
 */
public class ProductListResponse extends RBResponse {

    /**
     * key : 颜色
     * value : [{"id":"t1","name":"红色"},{"id":"t2","name":"绿色"}]
     */

    private List<ListFilterBean> list_filter;
    /**
     * key : 颜色
     * value : [{"id":"t1","name":"红色"},{"id":"t2","name":"绿色"}]
     */

    private List<CategoryProductlistBean> category_productlist;
    /**
     * comment_count : 100
     * id : 101
     * marketprice : 1000
     * name : 防辐射0
     * pic : images/0.png
     * price : 800
     */

    private List<ProductlistBean> productlist;

    public List<ListFilterBean> getList_filter() {
        return list_filter;
    }

    public void setList_filter(List<ListFilterBean> list_filter) {
        this.list_filter = list_filter;
    }

    public List<CategoryProductlistBean> getCategory_productlist() {
        return category_productlist;
    }

    public void setCategory_productlist(List<CategoryProductlistBean> category_productlist) {
        this.category_productlist = category_productlist;
    }

    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ListFilterBean {
        private String key;
        /**
         * id : t1
         * name : 红色
         */

        private List<ValueBean> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class CategoryProductlistBean {
        private String key;
        /**
         * id : t1
         * name : 红色
         */

        private List<ValueBean> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }

        public static class ValueBean {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class ProductlistBean {
        private int comment_count;
        private int id;
        private int marketprice;
        private String name;
        private String pic;
        private int price;

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
