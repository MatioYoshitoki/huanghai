package com.matio.mapping;

import com.matio.pojo.User;
import com.matio.pojo.UserCondition;
import com.matio.pojo.View_user;

import java.util.List;

public interface UserMapper {
    View_user selectByAccount(String account);

    List<View_user> selectByCondition(UserCondition userCondition);

    int selectCountByCondition(UserCondition userCondition);

    int insert(User record);

    int updateByAccount(User user);

    int deleteByAccount(String account);
}