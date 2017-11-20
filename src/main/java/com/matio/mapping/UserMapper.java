package com.matio.mapping;

import com.matio.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByAccount(String account);
}