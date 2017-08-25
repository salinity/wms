package com.salinity.wms.service;


import com.salinity.wms.common.web.MessageResult;
import com.salinity.wms.common.web.PageResponse;
import com.salinity.wms.pojo.dto.UserDTO;

/**
 * Created by Administrator on 2017/6/8.
 */
public interface IUserService {

    MessageResult getUser(Long id);

    PageResponse getUserPage(UserDTO userDTO);

    MessageResult addUser(UserDTO userDTO);
}
