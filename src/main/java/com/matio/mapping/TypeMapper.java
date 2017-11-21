package com.matio.mapping;

import com.matio.pojo.Type;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectAll();

    Type selectByType(String type);

    Type selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}