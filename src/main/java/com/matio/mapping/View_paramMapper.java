package com.matio.mapping;

import com.matio.pojo.View_param;

import java.util.List;

public interface View_paramMapper {
    int insert(View_param record);

    List<View_param> selectAll();

    int insertSelective(View_param record);
}