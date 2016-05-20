package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by xiaoyan on 2016/4/1.
 *  地址列表信息
 */
public class AddresslistResponse extends RBResponse {

    /**
     * areadetail : 海定
     * areaid : 110101
     * cityid : 110100
     * fixedtel : 50000
     * id : 1
     * name : liu1
     * phonenumber : 13800001
     * provinceid : 110000
     * zipcode : 1000000
     */

    private List<AddresslistBean> addresslist;

    public List<AddresslistBean> getAddresslist() {
        return addresslist;
    }

    public void setAddresslist(List<AddresslistBean> addresslist) {
        this.addresslist = addresslist;
    }

    public static class AddresslistBean {
        private String areadetail;
        private int areaid;
        private int cityid;
        private String fixedtel;
        private int id;
        private String name;
        private String phonenumber;
        private int provinceid;
        private String zipcode;

        public String getAreadetail() {
            return areadetail;
        }

        public void setAreadetail(String areadetail) {
            this.areadetail = areadetail;
        }

        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public String getFixedtel() {
            return fixedtel;
        }

        public void setFixedtel(String fixedtel) {
            this.fixedtel = fixedtel;
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

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public int getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(int provinceid) {
            this.provinceid = provinceid;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
    }
}
