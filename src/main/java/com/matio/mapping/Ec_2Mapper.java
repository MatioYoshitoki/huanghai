package com.matio.mapping;

import com.matio.pojo.Ec_2;

public interface Ec_2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ec_2 record);

    int insertSelective(Ec_2 record);

    Ec_2 selectByPrimaryKey(Integer id);

    Ec_2 selectByEC1_IdAndEC2(Ec_2 ec2);

    int updateByPrimaryKeySelective(Ec_2 record);

    int updateByPrimaryKey(Ec_2 record);
}