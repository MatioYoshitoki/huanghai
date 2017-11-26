package com.matio.mapping;

import com.matio.pojo.Ec_1;

import java.util.List;

public interface Ec_1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ec_1 record);

    int insertSelective(Ec_1 record);

    List<Ec_1> selectByEC1(String ec1);

    List<Ec_1> selectAll();

    Ec_1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ec_1 record);

    int updateByPrimaryKey(Ec_1 record);
}