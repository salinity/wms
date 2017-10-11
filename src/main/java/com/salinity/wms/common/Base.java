package com.salinity.wms.common;


/**
 * Created by Administrator on 2017/6/9.
 */
public class Base {

    public ResponseSuc getMessageSuc() {
        return new ResponseSuc();
    }

    public ResponseFail getMessageFail() {
        return new ResponseFail();
    }
}
