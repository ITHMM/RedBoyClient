package com.itheima.redboyclient.bean;

import java.util.List;

/**
 * Created by xch on 2016/4/1.
 * 热门搜索
 */
public class SearchHotResponse extends RBResponse{


    private List<String> search_keywords;

    public List<String> getSearch_keywords() {
        return search_keywords;
    }

    public void setSearch_keywords(List<String> search_keywords) {
        this.search_keywords = search_keywords;
    }
}
