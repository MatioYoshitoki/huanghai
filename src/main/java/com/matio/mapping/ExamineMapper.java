package com.matio.mapping;

import com.matio.pojo.Examine;
import com.matio.pojo.MmeCondition;

import java.util.List;

public interface ExamineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Examine record);

    int insertSelective(Examine record);

    Examine selectByPrimaryKey(Integer id);
    List<Examine> selectAll();
    List<Examine> selectByCondition(MmeCondition mmeCondition);
    int deleteAll();

    int updateByPrimaryKeySelective(Examine record);

    int updateByPrimaryKey(Examine record);
}