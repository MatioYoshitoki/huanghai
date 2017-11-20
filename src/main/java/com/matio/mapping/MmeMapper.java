package com.matio.mapping;

import com.matio.pojo.Mme;

public interface MmeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mme record);

    int insertSelective(Mme record);

    Mme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mme record);

    int updateByPrimaryKey(Mme record);
}