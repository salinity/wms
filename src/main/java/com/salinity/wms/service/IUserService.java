package com.salinity.wms.service;


import com.salinity.wms.pojo.dto.UserDTO;

/**
 * Created by Administrator on 2017/6/8.
 */
public interface IUserService {

    ResponseResult getUser(Long id);

    PageResponse getUserPage(UserDTO userDTO);

    ResponseResult addUser(UserDTO userDTO);
}
