package com.itheima.redboyclient.bean;

/**
 * ━━━━ Code is far away from ━━━━━━<p/>
 * 　　  () 　　　  () <br/>
 * 　　  ( ) 　　　( ) <br/>
 * 　　  ( ) 　　　( ) <br/>
 * 　　┏┛┻━━━┛┻┓<br/>
 * 　　┃　　　━　　　┃<br/>
 * 　　┃　┳┛　┗┳　┃<br/>
 * 　　┃　　　┻　　　┃<br/>
 * 　　┗━┓　　　┏━┛<br/>
 * 　　　　┃　　　┃<br/>
 * 　　　　┃　　　┗━━━┓<br/>
 * 　　　　┃　　　　　　　┣┓<br/>
 * 　　　　┃　　　　　　　┏┛<br/>
 * 　　　　┗┓┓┏━┳┓┏┛<br/>
 * 　　　　　┃┫┫　┃┫┫<br/>
 * 　　　　　┗┻┛　┗┻┛<br/>
 * ━━━━ bug with the XYY protecting━━━<br/>
 * <br/>
 * Created by Seny on 2015/12/1.<br/>
 *
 * 服务器错误响应的封装对象
 * <br/>
 *   {<br/>
 *        "response": "error",<br/>
 *        "error": {<br/>
 *        "text": "用户名不存在"<br/>
 *           }<br/>
 *    }<br/>
 *
 */
public class ErrorResponse extends RBResponse {

    /**
     * text : 用户名不存在
     */

    private ErrorEntity error;

    public void setError(ErrorEntity error) {
        this.error = error;
    }

    public ErrorEntity getError() {
        return error;
    }

    public static class ErrorEntity {
        private String text;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
