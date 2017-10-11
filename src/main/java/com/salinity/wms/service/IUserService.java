package com.salinity.wms.service;


import com.salinity.wms.common.message.MessageResult;
import com.salinity.wms.common.repones.PageResponse;
import com.salinity.wms.common.repones.ResponseResult;
import com.salinity.wms.pojo.UserEntity;
import com.salinity.wms.pojo.dto.UserDTO;

/**
 * Created by Administrator on 2017/6/8.
 */
public interface IUserService {

    UserEntity findUserById(Long id);

    UserEntity findUserByUserName(String loginName);

    PageResponse getUserPage(UserDTO userDTO);

    MessageResult addUser(UserDTO userDTO);
}
