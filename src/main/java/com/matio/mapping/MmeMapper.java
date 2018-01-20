package com.matio.mapping;

import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;

import java.util.List;

public interface MmeMapper {

    String selectByLocus(String locus);

    String selectEC1ByEC2(String ec2);

    List<Mme> selectByCondition(MmeCondition mmeCondition);

    int selectCountByCondition(MmeCondition mmeCondition);

    int insert(Mme record);

    int updateIsModified(Mme mme);

    int updateIsModifiedAll(String isModified);

    int updateByPrimaryKeyWithBLOBs(Mme record);

    int updateByPrimaryKey(Mme record);

    int deleteByPrimaryKey(Integer id);
}