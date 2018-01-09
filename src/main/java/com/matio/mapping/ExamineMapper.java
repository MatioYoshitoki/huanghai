package com.matio.mapping;

import com.matio.pojo.Examine;
import com.matio.pojo.MmeCondition;


import java.util.List;

public interface ExamineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Examine record);
    List<Examine> selectAll();
    int deleteAll();
    int selectCountByCondition(MmeCondition mmeCondition);
    List<Examine> selectByCondition(MmeCondition mmeCondition);

    int insertSelective(Examine record);

    Examine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Examine record);

    int updateByPrimaryKeyWithBLOBs(Examine record);

    int updateByPrimaryKey(Examine record);
}