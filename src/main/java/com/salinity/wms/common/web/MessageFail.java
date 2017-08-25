package com.salinity.wms.common.web;

/**
 * Created by Administrator on 2017/6/9.
 */
public class MessageFail extends MessageResult{
    public MessageFail() {
        setState("fail");
        setCode("200");
    }
}
