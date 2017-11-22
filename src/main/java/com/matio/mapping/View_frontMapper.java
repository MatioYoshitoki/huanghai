package com.matio.mapping;

import com.matio.pojo.MmeCondition;
import com.matio.pojo.View_front;

import java.util.List;

public interface View_frontMapper {
    int insert(View_front record);

    int insertSelective(View_front record);

    List<View_front> selectByFuzzyEC2(MmeCondition mmeCondition);
}