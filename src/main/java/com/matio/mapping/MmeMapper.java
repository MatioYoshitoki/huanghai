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

    String selectByLocus(String locus);

    int selectCountByCondition(MmeCondition condition);

    String selectEC1ByEC2(String ec2);

    Mme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mme record);

    int updateByPrimaryKey(Mme record);

    int updateIsModified(Mme record);

    int updateIsModifiedAll(String isModified);
}