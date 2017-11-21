package com.matio.mapping;

import com.matio.pojo.View_param;

import java.util.List;

public interface View_paramMapper {
    int insert(View_param record);

    List<View_param> selectAll();

    List<View_param> selectEc2();

    List<View_param> selectType();

    int insertSelective(View_param record);
}