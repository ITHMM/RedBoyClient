package com.itheima.redboyclient.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZCM on 2016/3/31.
 */
public class CategoryResponse extends RBResponse {

    /**
     * category : [{"id":101,"isleafnode":false,"name":"孕妈专区","parent_id":0,"pic":"images/1.png",
     * "tag":"孕产营养品/奶粉"},{"id":102,"isleafnode":false,"name":"营养食品","parent_id":0,"pic":"images/1
     * .png","tag":"奶粉"},{"id":103,"isleafnode":true,"name":"成长用品","parent_id":0,"pic":"images/1
     * .png","tag":"尿裤/纸巾  婴幼儿洗浴护肤"},{"id":104,"isleafnode":true,"name":"玩具童车","parent_id":0,
     * "pic":"images/1.png","tag":"启智玩具  婴儿车"},{"id":105,"isleafnode":true,"name":"宝宝靓装",
     * "parent_id":0,"pic":"images/1.png","tag":"男童服饰  女童服饰"},{"id":106,"isleafnode":true,
     * "name":"图书","parent_id":0,"pic":"images/1.png","tag":"孕产妈妈  育儿/亲子"},{"id":10101,
     * "isleafnode":false,"name":"孕产营养品/奶粉","parent_id":101,"pic":"","tag":"孕产妈妈  育儿/亲子"},
     * {"id":10102,"isleafnode":false,"name":"防辐射","parent_id":101,"pic":"","tag":"孕产妈妈  育儿/亲子"},
     * {"id":10101,"isleafnode":false,"name":"奶粉","parent_id":102,"pic":"","tag":""},{"id":10102,
     * "isleafnode":false,"name":"辅食","parent_id":102,"pic":"","tag":""},{"id":1010101,
     * "isleafnode":true,"name":"妈妈营养品","parent_id":10101,"pic":"","tag":""},{"id":1010102,
     * "isleafnode":true,"name":"妈妈奶粉","parent_id":10101,"pic":"","tag":""},{"id":1010201,
     * "isleafnode":true,"name":"防辐射服","parent_id":10102,"pic":"","tag":""},{"id":1010202,
     * "isleafnode":true,"name":"防辐射眼镜","parent_id":10102,"pic":"","tag":""}]
     * version : 2
*/    private String version;
    /**
     * 分类浏览的集合
     */
    private List<CategoryEntity> category;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    /**
     * 分类浏览的集合
     */
    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public class CategoryEntity implements Serializable {
        private int id;
        private boolean isleafnode;
        private String name;
        private int parent_id;
        private String pic;
        private String tag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsleafnode() {
            return isleafnode;
        }

        public void setIsleafnode(boolean isleafnode) {
            this.isleafnode = isleafnode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
