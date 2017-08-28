package com.salinity.wms.service.impl;

import com.salinity.wms.WmsApplication;
import com.salinity.wms.pojo.dto.UserDTO;
import com.salinity.wms.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/8/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WmsApplication.class)
public class UserServiceTest {

    @Autowired
    IUserService userService;
    @Test
    public void getUserPage() throws Exception {
        UserDTO userDTO = new UserDTO();
        System.out.println(userService.getUserPage(userDTO));
    }

    @Test
    public void getUser() throws Exception {
    }

    @Test
    public void addUser() throws Exception {
    }

}