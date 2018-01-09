package com.matio.mapping;

import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;

import java.util.List;

public interface MmeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mme record);

    String selectByLocus(String locus);
    Mme selectByFuzzyEC2(MmeCondition mmeCondition);
    List<Mme> selectByCondition(MmeCondition mmeCondition);
    int selectCountByCondition(MmeCondition mmeCondition);
    String selectEC1ByEC2(String ec2);
    int updateIsModified(Mme mme);
    int updateIsModifiedAll(String isModified);


    int insertSelective(Mme record);

    Mme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mme record);

    int updateByPrimaryKeyWithBLOBs(Mme record);

    int updateByPrimaryKey(Mme record);
}