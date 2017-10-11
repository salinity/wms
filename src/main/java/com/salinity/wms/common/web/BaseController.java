package com.salinity.wms.common.web;


import com.salinity.wms.common.message.Messages;
import com.salinity.wms.common.message.MessageResult;
import com.salinity.wms.common.repones.ResponseResult;
import com.salinity.wms.constant.WmsConstants;
import com.salinity.wms.pojo.domain.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/6/9.
 */
public class BaseController{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    protected Messages messages;

    protected HttpSession getSession() {
        return request.getSession();
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }

    protected CurrentUser getSessionCurrentUser() {
        return (CurrentUser) getSession().getAttribute(WmsConstants.SESSION_KEY);
    }

    protected ResponseResult getMessage(String messageKey, Object ...params) {
        ResponseResult responseResult = new ResponseResult();
        if(messageKey.startsWith("S")){
            return getSucMessage(messageKey,params);
        }else if(messageKey.startsWith("E")){
            return getFaultMessage(messageKey,params);
        }
        return responseResult;
    }

    protected ResponseResult getMessage(MessageResult mr) {
        String messageKey = mr.getCode();
        ResponseResult responseResult = new ResponseResult();
        if(messageKey.startsWith("S")){
            responseResult = getSucMessage(messageKey,mr.getParams());
            responseResult.setData(mr.getResult());
            return responseResult;
        }else if(messageKey.startsWith("E")){
            return getFaultMessage(messageKey,mr.getParams());
        }
        return responseResult;
    }

    protected ResponseResult getSucMessage() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode("S00001");
        responseResult.setMessage(messages.getMessage("S00001"));
        return responseResult;
    }

    protected ResponseResult getSucMessage(String messageKey,Object ...params) {
        ResponseResult responseResult = new ResponseResult(messageKey,params);
        responseResult.setCode(messageKey);
        return responseResult;
    }

    protected ResponseResult getSucResultData(Object obj){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode("S00001");
        responseResult.setData(obj);
        return responseResult;
    }

    protected ResponseResult getFaultMessage() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode("E00001");
        responseResult.setMessage(messages.getMessage("E00001"));
        return responseResult;
    }

    protected ResponseResult getFaultMessage(String messageKey,Object ...params) {
        ResponseResult responseResult = new ResponseResult(messages);
        responseResult.setCode(messageKey);
        responseResult.setMessage(messages.getMessage(messageKey,params));
        return responseResult;
    }

}
