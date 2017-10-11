package com.salinity.wms.controller.main;

import com.salinity.wms.common.web.BaseController;
import com.salinity.wms.pojo.dto.UserDTO;
import com.salinity.wms.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/8.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    /**
     * 获取用户
     * @param httpServletRequest
     * @param userDTO
     * @return
     */
//    @RequestLimit(count = 6,time = 60000)
    @GetMapping("")
    @ResponseBody
    public PageResponse getUser(HttpServletRequest httpServletRequest, UserDTO userDTO) {
         return userService.getUserPage(userDTO);
    }

    /**
     * 搜索单个用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseResult getUser(@PathVariable Long id) {
        if(id == null || Long.valueOf(0L).equals(id)) {
            return getMessageFail().setMessage("请输入正确的id");
        }
        //日志输出测试
        return userService.getUser(id);
    }

    /**
     * 添加用户的方法
     * @param userDTO
     * @return
     */
    @PostMapping("")
    @ResponseBody
    public ResponseResult addUser(UserDTO userDTO) {
        userDTO.setCreateTime(new Date().getTime());
        userDTO.setUpdateTime(new Date().getTime());
        userDTO.setUpdateUser("admin");
        userDTO.setCreateUser("admin");
        return userService.addUser(userDTO);
    }

}
