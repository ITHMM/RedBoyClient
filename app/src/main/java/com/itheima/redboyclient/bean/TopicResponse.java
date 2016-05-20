package com.itheima.redboyclient.bean;

import org.seny.android.utils.PrintUtil;

import java.util.List;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p/>
 * Created by Seny on 2015/11/30.
 *
 *
 * 用于封装topic信息
 */
public class TopicResponse extends RBResponse {


    /**
     * id : 10000
     * name : 雅培金装0
     * pic : images/image1.png
     */

    private List<TopicEntity> topic;


    public void setTopic(List<TopicEntity> topic) {
        this.topic = topic;
    }


    public List<TopicEntity> getTopic() {
        return topic;
    }

    /**
     * Topic实体
     */
    public static class TopicEntity {
        private int id;
        private String name;
        private String pic;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }

        @Override
        public String toString() {
            return "TopicEntity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", pic='" + pic + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TopicResponse{" +
                "response='" + response + '\'' +
                ", topic=" + PrintUtil.printList(topic) +
                '}';
    }

}
