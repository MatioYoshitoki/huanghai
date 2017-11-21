package com.matio.mapping;

import com.matio.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByAccount(String account);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectAll();

    int updateByAccount(User record);

    User selectByAccount(String account);
}