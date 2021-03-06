package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.*;
import com.matio.pojo.*;
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
    @Autowired
    View_ecMapper view_ecMapper;

    @RequestMapping(value = "/getType", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getType(){
        JSONObject result ;
        try {
            List<Type> types = typeMapper.selectAll();
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,"");
            result.put(Keys.DATA,types);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETTYPEFAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/addType", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addType(
        @RequestParam(Keys.TYPE) String type
    ){
        JSONObject result ;
        try {
            Type new_type = new Type();
            new_type.setType(type);
            if(typeMapper.insert(new_type) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.ADDTYPEFAILD);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.ADDTYPESUCCESS);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.ADDTYPEFAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/deleteType", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteType(
            @RequestParam(Keys.ID) String id
    ){
        JSONObject result ;
        try {
            if(typeMapper.deleteByPrimaryKey(Integer.valueOf(id)) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.DELETETYPEFAILD);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.DELETETYPESUCCESS);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/getTypeAndEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getTypeAndEC2(){
        JSONObject result ;
        try {
            List<View_param> dataType = view_paramMapper.selectType();
            List<View_param> dataEc2 = view_paramMapper.selectEc2();
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
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,"");
            result.put(Keys.DATA,data);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETTYPEANDEC2_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/addEC1", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addEc_1(
            @RequestParam(Keys.EC1) String ec_1
    ){
        JSONObject result;
        try {
            Ec_1 add_ec1 = new Ec_1();
            add_ec1.setEc1(ec_1);
            if(ec_1Mapper.insert(add_ec1) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.ADD_EC1_FAILD);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            } else {
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.MSG,Errors.ADD_EC1_SUCCESS);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            }
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.ADD_EC1_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/deleteEC1", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteEc_1(
            @RequestParam(Keys.ID) String id
    ){
        JSONObject result;
        try {
            if(ec_1Mapper.deleteByPrimaryKey(Integer.valueOf(id)) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.DELETE_EC1_FAILD);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.DELETE_EC1_SUCCESS);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/getEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getEC2(
            @RequestParam(Keys.NUMBERPERPAGE) String numberPerPage,
            @RequestParam(Keys.STARTPOS) String startPos
    ){
        JSONObject result;
        try {
            MmeCondition condition = new MmeCondition();
            int startPos_int = Integer.valueOf(startPos);
            int numberPerPage_int = Integer.valueOf(numberPerPage);
            int startSize = (startPos_int - 1) * numberPerPage_int;
            condition.setStartSize(startSize);
            condition.setEndSize(numberPerPage_int);
            List<View_ec> ec2List = view_ecMapper.selectAll(condition);
            int count = view_ecMapper.selectAllCount();

            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,"");
            result.put(Keys.DATA,ec2List);
            result.put(Keys.COUNT, count);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETTYPEANDEC2_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/addEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addEc_2(
            @RequestParam(Keys.EC1ID) String ec1_id,
            @RequestParam(Keys.EC2) String ec_2
    ){
        JSONObject result;
        try {
            Ec_2 condition = new Ec_2();
            condition.setEc1id(Integer.valueOf(ec1_id));
            condition.setEc2(ec_2.toLowerCase());
            Ec_2 ec2 = ec_2Mapper.selectByEC1_IdAndEC2(condition);
            if(ec2 != null) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.DATA,new JSONObject());
                result.put(Keys.MSG,Errors.ADD_EC2_EXISTS);
                return result.toJSONString();
            }
            if(ec_2Mapper.insert(condition) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.DATA,new JSONObject());
                result.put(Keys.MSG,Errors.ADD_EC2_FAILD);
                return result.toJSONString();
            } else {
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.DATA,new JSONObject());
                result.put(Keys.MSG,Errors.ADD_EC2_SUCCESS);
                return result.toJSONString();
            }
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.ADD_EC2_FAILD);
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/deleteEC2", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteEc_2(
            @RequestParam(Keys.ID) String id
    ){
        JSONObject result;
        try {
            if(ec_2Mapper.deleteByPrimaryKey(Integer.valueOf(id)) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.DELETE_EC2_FAILD);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.DELETE_EC2_SUCCESS);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/getTypeCount", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getTypeCount(){
        JSONObject result ;
        try {
            List<View_type> dataType = view_paramMapper.selectTypeCount();
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,"");
            result.put(Keys.DATA,dataType);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETTYPEANDEC2_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }
}
