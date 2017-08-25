package com.salinity.wms.common.web;

/**
 * Created by Administrator on 2017/6/9.
 */
public class MessageResult {

    private String code = "200";
    private Object object;
    private String message;
    private String state = "success";

    public String getState() {
        return state;
    }

    public MessageResult setState(String state) {
        this.state = state;
        return this;
    }

    public String getCode() {
        return code;
    }

    public MessageResult setCode(String code) {
        this.code = code;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public MessageResult setObject(Object object) {
        this.object = object;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
