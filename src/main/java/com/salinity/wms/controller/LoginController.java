package com.salinity.wms.controller;

import com.salinity.wms.common.repones.ResponseResult;
import com.salinity.wms.common.until.IPUtil;
import com.salinity.wms.common.web.BaseController;
import com.salinity.wms.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Administrator on 2017/10/11.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userService;

    public ResponseResult login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd)throws Exception {
        String loginIP = IPUtil.getIPAddress(this.getRequest());
        logger.info(" user {} login, this login ip is {}", loginName, loginIP);

        if (StringUtils.isEmpty(loginName)) {
            return null;
        }
        return null;
    }

}
