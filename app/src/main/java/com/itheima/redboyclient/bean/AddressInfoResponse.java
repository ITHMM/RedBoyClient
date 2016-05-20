package com.itheima.redboyclient.bean;

/**
 * 封装结算中心数据的bean
 * Created by ly on 2016/4/1.
 */
public class AddressInfoResponse extends RBResponse {

    /**
     * id : 1001
     * name : 肖文
     * address_area : 北京市海淀区
     * address_detail : 闵庄路3号
     */

    public AddressInfoBean address_info;
    /**
     * type : 1
     */

    private PaymentInfoBean payment_info;
    /**
     * type : 1
     */

    private DeliveryInfoBean delivery_info;
    /**
     * title : 红孩子信息有限公司
     * content : 办公用品
     * id : 123
     */

    private InvoiceInfoBean invoice_info;

    public static class AddressInfoBean {
        private String id;
        private String name;
        private String address_area;
        private String address_detail;
        private String number;

        public void setNumber(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

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

        public String getAddress_area() {
            return address_area;
        }

        public void setAddress_area(String address_area) {
            this.address_area = address_area;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        @Override
        public String toString() {
            return "AddressInfoBean{" +
                    "address_area='" + address_area + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address_detail='" + address_detail + '\'' +
                    '}';
        }
    }

    public static class PaymentInfoBean {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class DeliveryInfoBean {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class InvoiceInfoBean {
        private String title;
        private String content;
        private String id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
