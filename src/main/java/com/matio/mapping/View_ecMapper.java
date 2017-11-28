package com.matio.mapping;

import com.matio.pojo.MmeCondition;
import com.matio.pojo.View_ec;

import java.util.List;

public interface View_ecMapper {
    int insert(View_ec record);

    int insertSelective(View_ec record);

    List<View_ec> selectAll(MmeCondition condition);

    int selectAllCount();
}