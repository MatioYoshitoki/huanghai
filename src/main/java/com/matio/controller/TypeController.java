package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.TypeMapper;
import com.matio.mapping.View_paramMapper;
import com.matio.pojo.Type;
import com.matio.pojo.View_param;
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/11/21.
 */
@RestController
public class TypeController {
    @Autowired
    TypeMapper typeMapper ;

    @Autowired
    View_paramMapper view_paramMapper ;

    @RequestMapping(value = "/getType", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getType(){
        List<Type> types = typeMapper.selectAll();
        JSONObject result ;
        if (types == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETTYPEFAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }else {
            result = JsonUtil.fromErrors(Errors.SUCCESS);
        }
        JSONArray data = new JSONArray();
        for (Type type:types){
            JSONObject tmp = new JSONObject();
            tmp.put(Keys.ID,type.getTid());
            tmp.put(Keys.TYPE,type.getType());
            data.add(tmp);
        }
        result.put(Keys.MSG,"");
        result.put(Keys.DATA,data);
        return result.toJSONString();
    }


    @RequestMapping(value = "/addType", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addType(
        @RequestParam(Keys.TYPE) String type
    ){
        Type type1 = typeMapper.selectByType(type);
        JSONObject result ;
        if (type1 == null){
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.ADDTYPESUCCESS);
            result.put(Keys.DATA,new JSONObject());
            Type new_type = new Type();
            new_type.setType(type);
            typeMapper.insert(new_type);
        }else {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.ADDTYPEFAILD);
            result.put(Keys.DATA,new JSONObject());
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/deleteType", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteType(
            @RequestParam(Keys.ID) String id
    ){
        Type type = typeMapper.selectByPrimaryKey(Integer.valueOf(id));
        JSONObject result ;
        if (type == null){

            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.DELETETYPEFAILD);
            result.put(Keys.DATA,new JSONObject());

        }else {

            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.DELETETYPESUCCESS);
            result.put(Keys.DATA,new JSONObject());

        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/getTypeAndEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getTypeAndEC2(){
        List<View_param> view_params = view_paramMapper.selectAll();
        JSONObject result ;
        if (view_params == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETTYPEANDEC2_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }else {
            result = JsonUtil.fromErrors(Errors.SUCCESS);
        }
        JSONObject data = new JSONObject();
        List<String> types = new ArrayList<>();
        List<String> ec2 = new ArrayList<>();
        for (View_param view_param:view_params){
            types.add(view_param.getType());
            ec2.add(view_param.getEc2());
        }
        data.put(Keys.TYPE,types);
        data.put(Keys.EC2,ec2);
        result.put(Keys.MSG,"");
        result.put(Keys.DATA,data);
        return result.toJSONString();
    }
}
