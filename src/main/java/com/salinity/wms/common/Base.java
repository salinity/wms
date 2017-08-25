package com.salinity.wms.common;


import com.salinity.wms.common.web.MessageFail;
import com.salinity.wms.common.web.MessageSuc;

/**
 * Created by Administrator on 2017/6/9.
 */
public class Base {

    public MessageSuc getMessageSuc() {
        return new MessageSuc();
    }

    public MessageFail getMessageFail() {
        return new MessageFail();
    }
}
