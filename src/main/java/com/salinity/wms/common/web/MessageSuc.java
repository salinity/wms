package com.salinity.wms.common.web;

/**
 * Created by Administrator on 2017/6/9.
 */
public class MessageSuc extends MessageResult {
    public MessageSuc (){
        setCode("200");
        setMessage("操作成功");
        setState("success");
    }
}
