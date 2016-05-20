package com.itheima.redboyclient.bean;


/**
 * Created by Administrator on 2016/4/1/001.
 */
public class RegisterResponse extends RBResponse {
/**
 *{
 "response": "register",
 "userinfo":{
 "userId": 30505,
 "usersession": "MD5"
 }
 }
 */


    private int userId;

    private String usersession;

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setUsersession(String usersession){
        this.usersession = usersession;
    }
    public String getUsersession(){
        return this.usersession;
    }
}
