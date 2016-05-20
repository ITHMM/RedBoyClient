package com.itheima.redboyclient.bean;

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
 * Created by Seny on 2015/12/1.
 *
 * 封装主页Response
 */
public class HomeResponse extends RBResponse {

    /**
     * home_banner : [{"id":0,"pic":"images/image1.png","title":"活动"},{"id":1,"pic":"images/image2.png","title":"活动"},{"id":2,"pic":"images/image3.png","title":"活动"},{"id":3,"pic":"images/image4.png","title":"活动"},{"id":4,"pic":"images/image5.png","title":"活动"}]
     * response : home
     */

    /**
     * id : 0
     * pic : images/image1.png
     * title : 活动
     */

    private List<HomeBannerEntity> home_banner;


    public void setHome_banner(List<HomeBannerEntity> home_banner) {
        this.home_banner = home_banner;
    }


    public List<HomeBannerEntity> getHome_banner() {
        return home_banner;
    }

    public static class HomeBannerEntity {
        private int id;
        private String pic;
        private String title;

        public void setId(int id) {
            this.id = id;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getPic() {
            return pic;
        }

        public String getTitle() {
            return title;
        }
    }
}
