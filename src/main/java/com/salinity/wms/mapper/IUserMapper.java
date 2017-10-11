package com.salinity.wms.mapper;

import com.salinity.wms.pojo.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
@Mapper
public interface IUserMapper {

    //    @Select("select * from user where id = #{id}")
    UserEntity selectByPrimaryKey(@Param("id") Long id);

    @Select("select * from user where login_name = #{loginName}")
    UserEntity selectUserByUserName(@Param("loginName") String loginName);

    List<UserEntity> selectUserPage(@Param("entity") UserEntity userEntity);

    //    @Select(value = "Select count(*) from service")
    int selectPageCount(@Param("entity") UserEntity userEntity);

    int saveUser(UserEntity userEntity);

    int modifyUser(UserEntity userEntity);

    @Delete(value = "Delete from service where id = #{userId}")
    int deleteUser(@Param("userId") Long userId);
}
