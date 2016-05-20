package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by xiaoyan on 2016/3/31.
 */
public class LogisticsResponse extends RBResponse {

    /**
     * expressway : 快递
     * list : ["到北京","已签收"]
     * logisticscorp : 顺丰
     * logisticsid : KKLI2324342424242
     */

    private LogisticsBean logistics;

    public LogisticsBean getLogistics() {
        return logistics;
    }

    public void setLogistics(LogisticsBean logistics) {
        this.logistics = logistics;
    }

    public static class LogisticsBean {
        private String expressway;
        private String logisticscorp;
        private String logisticsid;
        private List<String> list;

        public String getExpressway() {
            return expressway;
        }

        public void setExpressway(String expressway) {
            this.expressway = expressway;
        }

        public String getLogisticscorp() {
            return logisticscorp;
        }

        public void setLogisticscorp(String logisticscorp) {
            this.logisticscorp = logisticscorp;
        }

        public String getLogisticsid() {
            return logisticsid;
        }

        public void setLogisticsid(String logisticsid) {
            this.logisticsid = logisticsid;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
