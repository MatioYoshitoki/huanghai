package com.matio.mapping;

import com.matio.pojo.View_user;

public interface View_userMapper {
    int insert(View_user record);

    int insertSelective(View_user record);
}