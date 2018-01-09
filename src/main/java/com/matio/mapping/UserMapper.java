package com.matio.mapping;

import com.matio.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String useraccount);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String useraccount);

    List<User> selectAll();
    List<User> updateByAccount(User user);

    int deleteByAccount(String account);

    User selectByAccount(String account);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
}