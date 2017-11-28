package com.matio.mapping;

import com.matio.pojo.View_param;
import com.matio.pojo.View_type;

import java.util.List;

public interface View_paramMapper {
    int insert(View_param record);

    List<View_param> selectAll();

    List<View_param> selectEc2();

    List<View_param> selectType();

    List<View_type> selectTypeCount();

    int insertSelective(View_param record);
}