package com.matio.mapping;

import com.matio.pojo.Config;

import java.util.List;

public interface ConfigMapper {
    int deleteByPrimaryKey(String param);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectAll();
    Config selectByPrimaryKey(String param);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}