package com.itheima.redboyclient.bean;

/**
 * Created by xiaoyan on 2016/3/31.
 * 封装订单详情的信息
 */
public class OrderDetails extends RBResponse{

    /**
     * content : 办公用品
     * id : 132
     * title : 红孩子信息有限公司
     */

    private InvoiceInfoBean invoice_info;
    /**
     * address_area : 北京市海淀区
     * address_detail : 闵庄路3号
     * id : 1001
     * name : 肖文
     */

    private AddressInfoBean address_info;
    /**
     * invoice_info : {"content":"办公用品","id":132,"title":"红孩子信息有限公司"}
     * address_info : {"address_area":"北京市海淀区","address_detail":"闵庄路3号","id":1001,"name":"肖文"}
     * response : orderdetail
     * checkout_prom : ["促销信息一","促销信息二"]
     * delivery_info : {"type":1}
     * checkout _addup : {"freight":10,"prom_cut":0,"total_count":3,"total_point":0,"total_price":610}
     * payment_info : {"type":1}
     * order_info : {"flag":1,"orderid":"2324244","status":"已完成","time":"2011/10/100 12:16:40"}
     * productlist : [{"id":1001,"isgift":false,"name":"雅培金装","number":10,"pic":"images/1.png","price":100,"prodNum":1,"subtotal":100,"uplimit":10},{"id":1002,"isgift":false,"name":"雅培金装","number":10,"pic":"images/2.png","price":200,"prodNum":1,"subtotal":200,"uplimit":10},{"id":1003,"isgift":false,"name":"雅培金装","number":10,"pic":"images/3.png","price":300,"prodNum":1,"subtotal":300,"uplimit":10}]
     */

    private String response;
    /**
     * type : 1
     */

    private DeliveryInfoBean delivery_info;

    public static class InvoiceInfoBean {
        private String content;
        private int id;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class AddressInfoBean {
        private String address_area;
        private String address_detail;
        private int id;
        private String name;

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
    }

    public static class DeliveryInfoBean {
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
