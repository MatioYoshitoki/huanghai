package com.matio.mapping;

import com.matio.pojo.View_spider_record;

import java.util.List;

public interface View_spider_recordMapper {
    int insert(View_spider_record record);

    int insertSelective(View_spider_record record);

    List<View_spider_record> selectAll();
}