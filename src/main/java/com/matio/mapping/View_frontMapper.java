package com.matio.mapping;

import com.matio.pojo.MmeCondition;
import com.matio.pojo.View_front;

import java.util.List;

public interface View_frontMapper {
    List<View_front> selectByFuzzyEC2(MmeCondition mmeCondition);

    int selectCountByFuzzyEC2(MmeCondition mmeCondition);
}