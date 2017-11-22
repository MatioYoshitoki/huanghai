package com.matio.mapping;

import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;

import java.util.List;

public interface MmeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mme record);

    int insertSelective(Mme record);

    List<Mme> selectByCondition(MmeCondition condition);

    List<Mme> selectByFuzzyEC2(MmeCondition condition);

    Mme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mme record);

    int updateByPrimaryKey(Mme record);
}