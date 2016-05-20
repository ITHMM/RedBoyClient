package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by 李正春 on 2016/4/1.
 */
public class BrandResponse extends RBResponse {

    /**
     * 推荐品牌专区集合
     */
    private List<BrandBean> brand;
    /**
     * 获取推荐品牌专区集合
     */

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    /**
     *推荐品牌专区
     */

    public static class BrandBean {
        /**
         * 品牌专区的名字
         */
        private String key;
        /**
         * 推荐品牌详情集合
         */
        private List<ValueBean> value;
        /**
         * 获取品牌专区的名字
         */

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
        /**
         * 获取推荐品牌详情集合
         */

        public List<ValueBean> getValue() {
            return value;
        }

        public void setValue(List<ValueBean> value) {
            this.value = value;
        }
        /**
         * 推荐品牌详情的javaBean
         */

        public static class ValueBean {
            /**
             * 每个推荐品牌id
             */
            private int id;
            /**
             * 每个推荐品牌name
             */
            private String name;
            /**
             * 每个推荐品牌图片URL
             */
            private String pic;

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
        }
    }
}
