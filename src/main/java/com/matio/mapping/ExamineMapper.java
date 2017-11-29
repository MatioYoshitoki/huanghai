package com.matio.mapping;

import com.matio.pojo.Examine;
import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;

import java.util.List;

public interface ExamineMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteAll();

    int insert(Examine record);

    List<Examine> selectByCondition(MmeCondition condition);

    int insertSelective(Examine record);

    Examine selectByPrimaryKey(Integer id);
    List<Examine> selectAll();

    int updateByPrimaryKeySelective(Examine record);

    int updateByPrimaryKey(Examine record);
}