package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by ly on 2016/4/2.
 */
public class CartResponse extends RBResponse {

    /**
     * cart_addup : {"total_count":0,"total_point":0,"total_price":0}
     * cart_prom : []
     * productlist : []
     */

    public CartBean cart;

    public CartBean getCart() {
        return cart;
    }

    public void setCart(CartBean cart) {
        this.cart = cart;
    }

    public static class CartBean {
        /**
         * total_count : 0
         * total_point : 0
         * total_price : 0
         */

        public CartAddupBean cart_addup;
        public List<?> cart_prom;
        public List<?> productlist;

        public CartAddupBean getCart_addup() {
            return cart_addup;
        }

        public void setCart_addup(CartAddupBean cart_addup) {
            this.cart_addup = cart_addup;
        }

        public List<?> getCart_prom() {
            return cart_prom;
        }

        public void setCart_prom(List<?> cart_prom) {
            this.cart_prom = cart_prom;
        }

        public List<?> getProductlist() {
            return productlist;
        }

        public void setProductlist(List<?> productlist) {
            this.productlist = productlist;
        }

        public static class CartAddupBean {
            public int total_count;
            public int total_point;
            public int total_price;

            public int getTotal_count() {
                return total_count;
            }

            public void setTotal_count(int total_count) {
                this.total_count = total_count;
            }

            public int getTotal_point() {
                return total_point;
            }

            public void setTotal_point(int total_point) {
                this.total_point = total_point;
            }

            public int getTotal_price() {
                return total_price;
            }

            public void setTotal_price(int total_price) {
                this.total_price = total_price;
            }

            @Override
            public String toString() {
                return "CartAddupBean{" +
                        "total_count=" + total_count +
                        ", total_point=" + total_point +
                        ", total_price=" + total_price +
                        '}';
            }
        }
    }
}
