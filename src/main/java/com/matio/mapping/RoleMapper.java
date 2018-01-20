package com.matio.mapping;

import com.matio.pojo.Role;
import com.matio.pojo.UserCondition;

import java.util.List;

public interface RoleMapper {

    List<Role> selectAll(UserCondition userCondition);

    int selectCountAll();

    List<Role> selectRole();

    int insert(Role record);

    int updateByPrimaryKey(Role record);

    int deleteByPrimaryKey(Integer roleid);
}