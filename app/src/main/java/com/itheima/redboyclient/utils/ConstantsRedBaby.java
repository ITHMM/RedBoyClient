package com.itheima.redboyclient.utils;

public class ConstantsRedBaby {
    public static final String URL_SERVER = "http://192.168.31.197:8080/RedBabyServer/";
    public static final String URL_TOPIC = URL_SERVER + "topic";
    public static final String URL_HOME = URL_SERVER + "home";

    /**
     * 促销快报的Url
     */
    public static final String URL_PROM = URL_SERVER + "topic";
    /**
     * 推荐品牌的Url
     */
    public static final String URL_BRAND = URL_SERVER + "brand";
    //订单列表请求URL
    public static final String URL_ORDER_LIST = URL_SERVER + "orderlist";
    //取消订单请求URL
    public static final String URL_ORDER_CANCEL = URL_SERVER + "ordercancel";
    //收藏夹请求URL
    public static final String URL_FAVORITE = URL_SERVER + "favorites";
    //登录请求URL
    public static final String URL_LOGIN = URL_SERVER + "login";
    //注册请求URL
    public static final String URL_REGISTER = URL_SERVER + "register";
    //用户信息请求URL
    public static final String URL_USERINFO = URL_SERVER + "userinfo";
    /**
     * 搜索推荐的url
     */
    public static final String URL_SEARCH_HOT = URL_SERVER + "search/recommend";
    public static final String URL_ADDRESSLIST = URL_SERVER + "addresslist";
    /**
     * 新品上架URL
     */
    public static final String URL_NEWPRODUCT = URL_SERVER + "newproduct";
    /**
     * 热销单品URL
     */
    public static final String URL_HOTPRODUCT = URL_SERVER + "hotproduct";
    /**
     * 专题商品URL
     */
    public static final String URL_TOPICPLIST = URL_SERVER + "topic/plist";
    /**
     * 品牌商品URL
     */
    public static final String URL_BRANDPLIST = URL_SERVER + "brand/plist";
    /**
     * 商品特价URL
     */
    public static final String URL_LIMITBUY = URL_SERVER + "limitbuy";
    //分类请求URL
    public static final String URL_CATEGORY_LIST = URL_SERVER + "category";
    /**
     * 商品详情URL
     */
    public static final String URL_PRODUCT_DETAIL = URL_SERVER + "product";

    /**
     * zcm
     * 商品列表的URL
     */
    public static final String URL_PRODUCT_LIST = URL_SERVER + "productlist";

    /**
     * 搜索结果的url
     */
    public static final String URL_SEARCH_RESULT = URL_SERVER + "search";

    /**
     * topic请求码
     */
    public static final int REQUEST_CODE_TOPIC = 0;
    /**
     * home请求码
     */
    public static final int REQUEST_CODE_HOME = 1;
    /**
     * orderlist请求码
     */
    public static final int REQUEST_CODE_ORDERLIST = 2;
    /**
     * ordercancel请求码
     */
    public static final int REQUEST_CODE_ORDERCANCEL = 3;
    /**
     * 推荐平拍请求码
     */
    public static final int REQUEST_CODE_BRAND = 4;

    public static final int REQUEST_CODE_CATEGORY_LIST = 5;
    /**
     * MyFavoriteFragment请求码
     */
    public static final int REQUEST_CODE_MYFAVORITE = 6;
    /**
     * userinfo请求码
     */
    public static final int REQUEST_CODE_USERINFO = 7;
    /**
     * 登录请求码
     */
    public static final int REQUEST_CODE_LOGIN = 8;
    /**
     * 注册请求码
     */
    public static final int REQUEST_CODE_REGISTER = 9;
    /**
     * 地址
     */
    public static final int REQUEST_CODE_ADDRESSLIST = 10;

    /**
     * 热门搜索请求码
     */
    public static final int REQUEST_CODE_SEARCH = 11;
    /**
     * settlement的请求码
     */
    public static final int REQUEST_CODE_SETTLEMENT = 12;
    /**
     * 新品上架请求码
     */
    public static final int REQUEST_CODE_PRODUCT_DETAIL = 19;
    /**
     * 新品上架请求码
     */
    public static final int REQUEST_CODE_NEWPRODUCT = 19;
    /**
     * 热销单品的请求码
     */
    public static final int REQUEST_CODE_HOTPRODUCT = 18;
    /**
     * 专题商品的请求码
     */
    public static final int REQUEST_CODE_TOPICPLIST = 17;
    /**
     * 品牌商品的请求码
     */
    public static final int REQUEST_CODE_BRANDPLIST = 16;
    /**
     * 商品抢购的请求码
     */
    public static final int REQUEST_CODE_LIMITBUY = 15;
    /**
     * 购物车的返回码
     */
    public static final int REQUEST_CART = 20;

    /**
     * 搜索结果请求码
     */
    public static final int REQUEST_CODE_SEARCH_RESULT = 13;

    /**
     * zcm
     * 商品列表的请求码
     */
    public static final int REQUEST_CODE_PRODUCT_LIST = 25;
}
