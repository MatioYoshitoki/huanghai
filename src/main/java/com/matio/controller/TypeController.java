package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.Ec_1Mapper;
import com.matio.mapping.Ec_2Mapper;
import com.matio.mapping.TypeMapper;
import com.matio.mapping.View_paramMapper;
import com.matio.pojo.Ec_1;
import com.matio.pojo.Ec_2;
import com.matio.pojo.Type;
import com.matio.pojo.View_param;
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.View;
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

    @Autowired
    Ec_1Mapper ec_1Mapper;
    @Autowired
    Ec_2Mapper ec_2Mapper;

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
        List<View_param> dataType = view_paramMapper.selectType();
        List<View_param> dataEc2 = view_paramMapper.selectEc2();
        JSONObject result ;
        if (dataType == null && dataEc2 == null){
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
        for (View_param item:dataType){
            types.add(item.getType());
        }
        for(View_param item:dataEc2) {
            ec2.add(item.getEc2());
        }
        data.put(Keys.TYPE,types);
        data.put(Keys.EC2,ec2);
        result.put(Keys.MSG,"");
        result.put(Keys.DATA,data);
        return result.toJSONString();
    }

    @RequestMapping(value = "/addEC1", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addEc_1(
            @RequestParam(Keys.EC1) String ec_1
    ){
        List<Ec_1> ec_1s = ec_1Mapper.selectByEC1(ec_1);
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (ec_1s == null || ec_1s.size() == 0){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.ADD_EC1_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        Ec_1 add_ec1 = new Ec_1();
        add_ec1.setEc1(ec_1);
        ec_1Mapper.insert(add_ec1);
        result.put(Keys.MSG,Errors.ADD_EC1_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();

    }

    @RequestMapping(value = "/deleteEC1", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteEc_1(
            @RequestParam(Keys.ID) String id
    ){
        Ec_1 ec_1s = ec_1Mapper.selectByPrimaryKey(Integer.valueOf(id));
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (ec_1s == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.DELETE_EC1_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        ec_1Mapper.deleteByPrimaryKey(Integer.valueOf(id));
        result.put(Keys.MSG,Errors.DELETE_EC1_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();
    }

    @RequestMapping(value = "/addEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addEc_2(
            @RequestParam(Keys.EC1ID) String ec1_id,
            @RequestParam(Keys.EC2) String ec_2
    ){
        Ec_2 condition = new Ec_2();
        condition.setEc1id(Integer.valueOf(ec1_id));
        condition.setEc2(ec_2);
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        Ec_2 ec2 = ec_2Mapper.selectByEC1_IdAndEC2(condition);
        if (ec2 == null){
            ec_2Mapper.insert(condition);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.ADD_EC2_SUCCESS);
        }else {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.ADD_EC2_FAILD);
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/deleteEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteEc_2(
            @RequestParam(Keys.ID) String id
    ){
        Ec_2 ec_2s = ec_2Mapper.selectByPrimaryKey(Integer.valueOf(id));
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (ec_2s == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.DELETE_EC2_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        ec_1Mapper.deleteByPrimaryKey(Integer.valueOf(id));
        result.put(Keys.MSG,Errors.DELETE_EC2_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();
    }

}
