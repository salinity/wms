package com.salinity.wms.service.impl;

import com.salinity.wms.common.BaseService;
import com.salinity.wms.common.message.MessageResult;
import com.salinity.wms.common.repones.PageResponse;
import com.salinity.wms.common.untils.BeanUtils;
import com.salinity.wms.mapper.IUserMapper;
import com.salinity.wms.pojo.UserEntity;
import com.salinity.wms.pojo.dto.UserDTO;
import com.salinity.wms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
@Service
public class UserService extends BaseService implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public PageResponse getUserPage(UserDTO userDTO) {
        UserEntity userEntity = BeanUtils.copyBeanPropertyUtils(userDTO,UserEntity.class);
        List<UserEntity> list = userMapper.selectUserPage(userEntity);
        for (UserEntity entity : list) {
            entity.setLoginPwd("");
            if (entity.getCreateTime() == null) {
                entity.setCreateTime(new Date().getTime());
            }
            if (entity.getUpdateTime() == null) {
                entity.setUpdateTime(new Date().getTime());
            }
        }
        Integer total = userMapper.selectPageCount(userEntity);
        PageResponse pageResponse = new PageResponse();
        pageResponse.setRows(list);
        pageResponse.setTotal(total);
        return pageResponse;
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public MessageResult addUser(UserDTO userDTO) {
        UserEntity userEntity = BeanUtils.copyBeanPropertyUtils(userDTO, UserEntity.class);
        userMapper.saveUser(userEntity);
        return MessageResult.getSucMessage();
    }

    @Override
    public UserEntity findUserByUserName(String loginName) {
        return userMapper.selectUserByUserName(loginName);
    }
}
