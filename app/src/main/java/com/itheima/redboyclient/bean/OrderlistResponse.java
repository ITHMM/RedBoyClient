package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by xiaoyan on 2016/3/30.
 * 封装订单列表的信息
 */
public class OrderlistResponse extends RBResponse{

    /**
     * response : orderlist
     * orderlist : [{"flag":1,"orderid":"412423145","price":1234.23,"status":"未处理","time":"2011/10/100 12:16:40"},{"flag":3,"orderid":"412423146","price":200.23,"status":"已处理","time":"2011/10/100 12:18:40"},{"flag":3,"orderid":"412423147","price":1234.23,"status":"交易成功","time":"2011/10/100 12:16:40"},{"flag":1,"orderid":"412423148","price":1234.23,"status":"未处理","time":"2011/10/100 12:20:40"},{"flag":1,"orderid":"412423149","price":1234.23,"status":"未处理","time":"2011/10/100 12:16:40"},{"flag":3,"orderid":"412423150","price":200.23,"status":"已处理","time":"2011/10/100 12:18:40"},{"flag":3,"orderid":"412423151","price":1234.23,"status":"交易成功","time":"2011/10/100 12:16:40"},{"flag":1,"orderid":"412423152","price":1234.23,"status":"未处理","time":"2011/10/100 12:20:40"},{"flag":1,"orderid":"412423153","price":1234.23,"status":"未处理","time":"2011/10/100 12:16:40"},{"flag":3,"orderid":"412423154","price":200.23,"status":"已处理","time":"2011/10/100 12:18:40"},{"flag":3,"orderid":"412423155","price":200.23,"status":"已处理","time":"2011/10/100 12:18:40"},{"flag":3,"orderid":"412423156","price":200.23,"status":"已处理","time":"2011/10/100 12:18:40"}]
     */

    /**
     * flag : 1
     * orderid : 412423145
     * price : 1234.23
     * status : 未处理
     * time : 2011/10/100 12:16:40
     */

    public List<OrderlistBean> orderlist;

    public List<OrderlistBean> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistBean> orderlist) {
        this.orderlist = orderlist;
    }

    public static class OrderlistBean {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrderlistBean that = (OrderlistBean) o;

            if (flag != that.flag) return false;
            if (Double.compare(that.price, price) != 0) return false;
            if (orderid != null ? !orderid.equals(that.orderid) : that.orderid != null)
                return false;
            if (status != null ? !status.equals(that.status) : that.status != null) return false;
            return !(time != null ? !time.equals(that.time) : that.time != null);

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = flag;
            result = 31 * result + (orderid != null ? orderid.hashCode() : 0);
            temp = Double.doubleToLongBits(price);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (status != null ? status.hashCode() : 0);
            result = 31 * result + (time != null ? time.hashCode() : 0);
            return result;
        }

        private int flag;
        private String orderid;
        private double price;
        private String status;
        private String time;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
