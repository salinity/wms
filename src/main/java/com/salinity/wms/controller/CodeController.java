package com.salinity.wms.controller;

import com.salinity.wms.common.web.BaseController;
import com.salinity.wms.pojo.UserEntity;
import com.salinity.wms.service.IUserService;
import com.salinity.wms.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/25.
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController{

    @Autowired
    IUserService userService;

    @GetMapping("")
    @ResponseBody
    public UserEntity getCode() {
        UserEntity userEntity = RedisUtils.getItem("UserEntity:1");
        if (userEntity == null) {
            userEntity = userService.findUserById(Long.valueOf(1L));
            if (userEntity != null) {
                RedisUtils.putItem("UserEntity:1", userEntity);
            }
        }
        return userEntity;
    }

}
