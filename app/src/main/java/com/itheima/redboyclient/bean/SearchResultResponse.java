package com.itheima.redboyclient.bean;

import java.util.Comparator;
import java.util.List;

/**
 * Created by xch on 2016/4/1.
 * 搜索结果
 */
public class SearchResultResponse extends RBResponse{

    /**
     * list_count : 50
     * productlist : [{"comment_count":100,"id":10000,"marketprice":1001,"name":"雅培金装0","pic":"images/search1.png","price":802},{"comment_count":101,"id":10001,"marketprice":1001,"name":"雅培金装1","pic":"images/search1.png","price":802},{"comment_count":102,"id":10002,"marketprice":1001,"name":"雅培金装2","pic":"images/search1.png","price":802},{"comment_count":103,"id":10003,"marketprice":1001,"name":"雅培金装3","pic":"images/search1.png","price":802},{"comment_count":104,"id":10004,"marketprice":1001,"name":"雅培金装4","pic":"images/search1.png","price":802},{"comment_count":105,"id":10005,"marketprice":1001,"name":"雅培金装5","pic":"images/search1.png","price":802},{"comment_count":106,"id":10006,"marketprice":1001,"name":"雅培金装6","pic":"images/search1.png","price":802},{"comment_count":107,"id":10007,"marketprice":1001,"name":"雅培金装7","pic":"images/search1.png","price":802},{"comment_count":108,"id":10008,"marketprice":1001,"name":"雅培金装8","pic":"images/search1.png","price":802},{"comment_count":109,"id":10009,"marketprice":1001,"name":"雅培金装9","pic":"images/search1.png","price":802},{"comment_count":110,"id":10010,"marketprice":1001,"name":"雅培金装10","pic":"images/search1.png","price":802},{"comment_count":111,"id":10011,"marketprice":1001,"name":"雅培金装11","pic":"images/search1.png","price":802},{"comment_count":112,"id":10012,"marketprice":1001,"name":"雅培金装12","pic":"images/search1.png","price":802},{"comment_count":113,"id":10013,"marketprice":1001,"name":"雅培金装13","pic":"images/search1.png","price":802},{"comment_count":114,"id":10014,"marketprice":1001,"name":"雅培金装14","pic":"images/search1.png","price":802},{"comment_count":115,"id":10015,"marketprice":1001,"name":"雅培金装15","pic":"images/search1.png","price":802},{"comment_count":116,"id":10016,"marketprice":1001,"name":"雅培金装16","pic":"images/search1.png","price":802},{"comment_count":117,"id":10017,"marketprice":1001,"name":"雅培金装17","pic":"images/search1.png","price":802},{"comment_count":118,"id":10018,"marketprice":1001,"name":"雅培金装18","pic":"images/search1.png","price":802},{"comment_count":119,"id":10019,"marketprice":1001,"name":"雅培金装19","pic":"images/search1.png","price":802},{"comment_count":120,"id":10020,"marketprice":1001,"name":"雅培金装20","pic":"images/search1.png","price":802},{"comment_count":121,"id":10021,"marketprice":1001,"name":"雅培金装21","pic":"images/search1.png","price":802},{"comment_count":122,"id":10022,"marketprice":1001,"name":"雅培金装22","pic":"images/search1.png","price":802},{"comment_count":123,"id":10023,"marketprice":1001,"name":"雅培金装23","pic":"images/search1.png","price":802},{"comment_count":124,"id":10024,"marketprice":1001,"name":"雅培金装24","pic":"images/search1.png","price":802},{"comment_count":125,"id":10025,"marketprice":1001,"name":"雅培金装25","pic":"images/search1.png","price":802},{"comment_count":126,"id":10026,"marketprice":1001,"name":"雅培金装26","pic":"images/search1.png","price":802},{"comment_count":127,"id":10027,"marketprice":1001,"name":"雅培金装27","pic":"images/search1.png","price":802},{"comment_count":128,"id":10028,"marketprice":1001,"name":"雅培金装28","pic":"images/search1.png","price":802},{"comment_count":129,"id":10029,"marketprice":1001,"name":"雅培金装29","pic":"images/search1.png","price":802},{"comment_count":130,"id":10030,"marketprice":1001,"name":"雅培金装30","pic":"images/search1.png","price":802},{"comment_count":131,"id":10031,"marketprice":1001,"name":"雅培金装31","pic":"images/search1.png","price":802},{"comment_count":132,"id":10032,"marketprice":1001,"name":"雅培金装32","pic":"images/search1.png","price":802},{"comment_count":133,"id":10033,"marketprice":1001,"name":"雅培金装33","pic":"images/search1.png","price":802},{"comment_count":134,"id":10034,"marketprice":1001,"name":"雅培金装34","pic":"images/search1.png","price":802},{"comment_count":135,"id":10035,"marketprice":1001,"name":"雅培金装35","pic":"images/search1.png","price":802},{"comment_count":136,"id":10036,"marketprice":1001,"name":"雅培金装36","pic":"images/search1.png","price":802},{"comment_count":137,"id":10037,"marketprice":1001,"name":"雅培金装37","pic":"images/search1.png","price":802},{"comment_count":138,"id":10038,"marketprice":1001,"name":"雅培金装38","pic":"images/search1.png","price":802},{"comment_count":139,"id":10039,"marketprice":1001,"name":"雅培金装39","pic":"images/search1.png","price":802},{"comment_count":140,"id":10040,"marketprice":1001,"name":"雅培金装40","pic":"images/search1.png","price":802},{"comment_count":141,"id":10041,"marketprice":1001,"name":"雅培金装41","pic":"images/search1.png","price":802},{"comment_count":142,"id":10042,"marketprice":1001,"name":"雅培金装42","pic":"images/search1.png","price":802},{"comment_count":143,"id":10043,"marketprice":1001,"name":"雅培金装43","pic":"images/search1.png","price":802},{"comment_count":144,"id":10044,"marketprice":1001,"name":"雅培金装44","pic":"images/search1.png","price":802},{"comment_count":145,"id":10045,"marketprice":1001,"name":"雅培金装45","pic":"images/search1.png","price":802},{"comment_count":146,"id":10046,"marketprice":1001,"name":"雅培金装46","pic":"images/search1.png","price":802},{"comment_count":147,"id":10047,"marketprice":1001,"name":"雅培金装47","pic":"images/search1.png","price":802},{"comment_count":148,"id":10048,"marketprice":1001,"name":"雅培金装48","pic":"images/search1.png","price":802},{"comment_count":149,"id":10049,"marketprice":1001,"name":"雅培金装49","pic":"images/search1.png","price":802}]
     */

    private int list_count;
    /**
     * comment_count : 100
     * id : 10000
     * marketprice : 1001
     * name : 雅培金装0
     * pic : images/search1.png
     * price : 802
     */

    private List<ProductlistBean> productlist;

    public int getList_count() {
        return list_count;
    }

    public void setList_count(int list_count) {
        this.list_count = list_count;
    }

    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ProductlistBean  {

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
