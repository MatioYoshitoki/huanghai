package com.matio.mapping;

import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MmeMapper {

    String selectByLocus(String locus);

    String selectEC1ByEC2(String ec2);

    List<Mme> selectByFront(@Param("list") String list);

    List<Mme> selectByCondition(MmeCondition mmeCondition);

    int selectCountByFront(@Param("search") String search);

    int selectCountByCondition(MmeCondition mmeCondition);

    int insert(Mme record);

    int updateIsModified(Mme mme);

    int updateIsModifiedAll(String isModified);

    int updateByPrimaryKeyWithBLOBs(Mme record);

    int updateByPrimaryKey(Mme record);

    int deleteByPrimaryKey(Integer id);
}