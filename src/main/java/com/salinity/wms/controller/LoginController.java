package com.salinity.wms.controller;

import com.salinity.wms.common.repones.ResponseResult;
import com.salinity.wms.common.untils.BeanUtils;
import com.salinity.wms.common.untils.IPUtil;
import com.salinity.wms.common.untils.Md5Util;
import com.salinity.wms.common.web.BaseController;
import com.salinity.wms.constant.WmsConstants;
import com.salinity.wms.pojo.UserEntity;
import com.salinity.wms.pojo.domain.CurrentUser;
import com.salinity.wms.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Administrator on 2017/10/11.
 */
@Controller
@RequestMapping("/wms")
public class LoginController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userService;

    /**
     * user login
     * @param loginName
     * @param loginPwd
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd)throws Exception {
        String loginIP = IPUtil.getIPAddress(this.getRequest());
        logger.info(" user {} login, this login ip is {}", loginName, loginIP);

        if (StringUtils.isEmpty(loginName)) {
            return getFaultMessage("E00100");
        }
        if (StringUtils.isEmpty(loginPwd)) {
            return getFaultMessage("E00101");
        }
        UserEntity userEntity = userService.findUserByUserName(loginName);
        if (userEntity == null) {
            return getFaultMessage("E00102");
        }
        String validatePassword = Md5Util.decryptMd5(loginPwd + userEntity.getLoginName());
        if (!userEntity.getLoginName().equals(loginName) || !validatePassword.equals(userEntity.getLoginPwd())){
            return getFaultMessage("E00103");
        }
        if ("0".equals(String.valueOf(userEntity.getActive())) || "1".equals(String.valueOf(userEntity.getDel()))){
            logger.info("DId not find the account or the account is not activated!");
            return getFaultMessage("E00104");
        }
        // 对象拷贝
        CurrentUser currentUser = BeanUtils.copyBeanPropertyUtils(userEntity, CurrentUser.class);
        ResponseResult responseResult = getSucMessage();
        this.getSession().setAttribute(WmsConstants.SESSION_KEY, currentUser);
        responseResult.setData(currentUser);
        return responseResult;
    }

    /**
     * user loginOut
     * @param response
     * @throws IOException
     */
    @GetMapping("loginOut")
    public void loginOut (HttpServletResponse response) throws IOException {
        getRequest().getSession().removeAttribute(WmsConstants.SESSION_KEY);
        response.sendRedirect("/#/login");
    }

}
