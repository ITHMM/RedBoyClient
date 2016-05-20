package com.itheima.redboyclient.bean;

/**
 *  用户信息
 */
public class UserInfoResponse extends RBResponse {
    private UserinfoBean userinfo;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    /**
     "response": "userinfo",
     "userinfo":{ [积分，等级]
     "bonus": 3002,                  //会员积分
     "level": "金卡",                 //会员等级
     "userId": 30505,
     "usersession": "MD5",
     "ordercount":"20",                      //所下订单数
     "favoritescount":"12"                     //收藏总数
     }
     */
    public static class UserinfoBean {
        private int bonus;
        private String level;
        private int userId;
        private String usersession;
        private int ordercount;
        private int favoritescount;

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setOrdercount(int ordercount) {
            this.ordercount = ordercount;
        }

        public int getOrdercount() {
            return ordercount;
        }

        public int getFavoritescount() {
            return favoritescount;
        }

        public void setFavoritescount(int favoritescount) {
            this.favoritescount = favoritescount;
        }

        public String getUsersession() {
            return usersession;
        }

        public void setUsersession(String usersession) {
            this.usersession = usersession;
        }
    }
}
