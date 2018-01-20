package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.ConfigMapper;
import com.matio.mapping.Ec_1Mapper;
import com.matio.mapping.TypeMapper;
import com.matio.pojo.Config;
import com.matio.pojo.Ec_1;
import com.matio.pojo.Type;
import com.matio.pojo.User;
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/11/26.
 */
@RestController
public class ConfigController {
    @Autowired
    ConfigMapper configMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    Ec_1Mapper ec_1Mapper;

    @RequestMapping(value = "/getConfigList", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getConfigList(){
        JSONObject result;
        try {
            List<Config> configs = configMapper.selectAll();
            JSONArray data = new JSONArray();
            for (Config config:configs) {
                JSONObject every_config = new JSONObject();
                every_config.put(Keys.PARAM,config.getParam());
                every_config.put(Keys.VALUE,new String(config.getValue(), "utf8"));
                data.add(every_config);
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,"");
            result.put(Keys.DATA,data);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GET_CONFIG_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/modifyConfig", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String modifyConfig(
        @RequestParam(Keys.INDEX_TITLE) String index_title,
        @RequestParam(Keys.INDEX_CONTENT) String index_content,
        @RequestParam(Keys.INDEX_BANNER_TITLE) String index_banner_title,
        @RequestParam(Keys.INDEX_BANNER_CONTENT) String index_banner_content,
        @RequestParam(Keys.INDEX_LINK_TEXT) String index_link_text,
        @RequestParam(Keys.INDEX_LINK_URL) String index_link_url,
        @RequestParam(Keys.BROWSE_TITLE) String browse_title,
        @RequestParam(Keys.BROWSE_CONTENT) String browse_content,
        @RequestParam(Keys.ABOUTUS) String aboutUs
    ){
        Config update = new Config();
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        int back = 0;
        try {
            update.setParam(Keys.INDEX_TITLE);
            update.setValue(index_title.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.INDEX_CONTENT);
            update.setValue(index_content.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.INDEX_BANNER_TITLE);
            update.setValue(index_banner_title.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.INDEX_BANNER_CONTENT);
            update.setValue(index_banner_content.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.INDEX_LINK_TEXT);
            update.setValue(index_link_text.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.INDEX_LINK_URL);
            update.setValue(index_link_url.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.BROWSE_TITLE);
            update.setValue(browse_title.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.BROWSE_CONTENT);
            update.setValue(browse_content.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);

            update.setParam(Keys.ABOUTUS);
            update.setValue(aboutUs.getBytes("utf8"));
            back += configMapper.updateByPrimaryKey(update);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (back < 7){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.MODIFY_CONFIG_FAILD);
            result.put(Keys.DATA,new JSONObject());
        }else {
            result.put(Keys.MSG,Errors.MODIFY_CONFIG_SUCCESS);
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/getDataConfigList", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getDataConfigList(){
        List<Ec_1> ec_1s = ec_1Mapper.selectAll();
        List<Type> types = typeMapper.selectAll();
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (ec_1s==null || types == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GET_DATA_CONFIG_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        JSONArray type_jsa = new JSONArray();
        JSONArray ec_1_jsa = new JSONArray();
        JSONObject data = new JSONObject();
        ec_1s.forEach(one -> {
            JSONObject tmp = new JSONObject();
            tmp.put(Keys.ID,one.getId());
            tmp.put(Keys.EC1,one.getEc1());
            ec_1_jsa.add(tmp);
        });
        types.forEach(one -> {
            JSONObject tmp = new JSONObject();
            tmp.put(Keys.ID,one.getTid());
            tmp.put(Keys.TYPE,one.getType());
            type_jsa.add(tmp);
        });

        data.put(Keys.TYPE,type_jsa);
        data.put(Keys.EC1,ec_1_jsa);
        result.put(Keys.DATA,data);
        result.put(Keys.MSG,"");
        return result.toJSONString();
    }



}
