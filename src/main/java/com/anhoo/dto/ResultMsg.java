package com.anhoo.dto;

/**
 * author XueYuan
 * data  2017/05/03 20:48
 */
public class ResultMsg {


    /**100为成功 200为失败**/
    private String code;

    /**提示信息**/
    private String msg;

    /**返回内容**/
    private Object content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
