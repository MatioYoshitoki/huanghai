package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.DefaultValue;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.MmeMapper;
import com.matio.mapping.RoleMapper;
import com.matio.mapping.UserMapper;
import com.matio.pojo.Mme;
import com.matio.pojo.Role;
import com.matio.pojo.User;
import com.matio.unit.JsonUtil;
import com.matio.unit.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/8/29.
 */

@RestController
public class AccountController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MmeMapper mmeMapper;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping(value = "/login", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String login(
            @RequestParam(Keys.ACCOUNT) String account,
            @RequestParam(Keys.PASSWORD) String password
    ) {

        User user = userMapper.selectByAccount(account);

        JSONObject result ;
        if (user!=null && user.getPassword().equals(MD5Util.encode32(password))){
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,"");
            JSONObject data = new JSONObject();
            data.put(Keys.USERACCOUNT,user.getUseraccount());
            data.put(Keys.USERNAME,user.getUsername());
            data.put(Keys.USERGENDER,user.getUsergender());
            Role role = roleMapper.selectByPrimaryKey(user.getUserrole());
            if (role != null){
                data.put(Keys.ROLENAME,role.getRolename());
                data.put(Keys.ROLEAUTH,role.getRoleauth());
                result.put(Keys.DATA,data);
            }else {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.NO_SUCH_NAME_WRONG_PASSWORD);
                result.put(Keys.DATA,new JSONObject());
            }

        }else {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.NO_SUCH_NAME_WRONG_PASSWORD);
            result.put(Keys.DATA,new JSONObject());
        }


        return result.toJSONString();
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String regist(
            @RequestParam(Keys.USERACCOUNT) String userAccount,
            @RequestParam(Keys.USERNAME) String userName,
            @RequestParam(Keys.USERGENDER) String userGender,
            @RequestParam(Keys.USERROLE) String userRole,
            @RequestParam(Keys.OPERATOR) String operator
    ){
        User user = userMapper.selectByAccount(userAccount);
        JSONObject result ;
        if (user != null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.NEW_CREATE_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        Date now = new Date();
        User new_user = new User();
        new_user.setOperator(operator);
        new_user.setUseraccount(userAccount);
        new_user.setUsername(userName);
        new_user.setUsergender(userGender);
        new_user.setUserrole(Integer.valueOf(userRole));

        new_user.setPassword(MD5Util.encode32(DefaultValue.DEFAULT_PASSWORD));
        new_user.setUsercreate(simpleDateFormat.format(now));
        new_user.setUsermodify(simpleDateFormat.format(now));
        userMapper.insert(new_user);

        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.NEW_CREATE_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();

    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String modify(
            @RequestParam(Keys.USERACCOUNT) String userAccount,
            @RequestParam(Keys.USERNAME) String userName,
            @RequestParam(Keys.USERGENDER) String userGender,
            @RequestParam(Keys.USERROLE) String userRole,
            @RequestParam(Keys.OPERATOR) String operator
    ){
        User user = userMapper.selectByAccount(userAccount);
        JSONObject result ;
        if (user == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.MODIFY_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }

        Date now = new Date();
        User modify_user = new User();
        modify_user.setUserid(user.getUserid());
        modify_user.setOperator(operator);
        modify_user.setUseraccount(userAccount);
        modify_user.setUsername(userName);
        modify_user.setUsergender(userGender);
        modify_user.setUserrole(Integer.valueOf(userRole));

        modify_user.setUsermodify(simpleDateFormat.format(now));
        userMapper.updateByPrimaryKey(modify_user);

        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.MODIFY_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteUser(
            @RequestParam(Keys.USERID) String userId
    ){
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        JSONObject result ;
        if (user == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.DELETE_FAILE);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        userMapper.deleteByPrimaryKey(Integer.valueOf(userId));
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.DELETE_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getUserList(){
        List<User> users = userMapper.selectAll();
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        JSONArray data = new JSONArray();
        for (User user:users){
            JSONObject every_user = new JSONObject();
            every_user.put(Keys.USERID,user.getUserid());
            every_user.put(Keys.USERNAME,user.getUsername());
            every_user.put(Keys.USERGENDER,user.getUsergender());
            Role role = roleMapper.selectByPrimaryKey(user.getUserrole());
            if (role==null){
                continue;
            }
            every_user.put(Keys.USERROLE,user.getUserrole());
            every_user.put(Keys.ROLENAME,role.getRolename());
            every_user.put(Keys.USERCREATE,user.getUsercreate());
            every_user.put(Keys.USERMODIFY,user.getUsermodify());
            every_user.put(Keys.OPERATOR,user.getOperator());
            data.add(every_user);
        }
        result.put(Keys.MSG,"");
        result.put(Keys.DATA,data);
        return result.toJSONString();
    }

    @RequestMapping(value = "/createRole", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String createRole(
            @RequestParam(Keys.ROLENAME) String roleName,
            @RequestParam(Keys.ROLEAUTH) String roleAuth,
            @RequestParam(Keys.OPERATOR) String operator
    ){
        Role role = roleMapper.selectByRoleName(roleName);
        JSONObject result;
        if (role == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.NEW_ROLE_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        Role new_role = new Role();
        Date now = new Date();

        new_role.setOperator(operator);
        new_role.setRoleauth(roleAuth);
        new_role.setRolename(roleName);

        new_role.setRolecreate(simpleDateFormat.format(now));
        new_role.setRolemodify(simpleDateFormat.format(now));
        roleMapper.insert(new_role);
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.NEW_ROLE_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();
    }

    @RequestMapping(value = "/modifyRole", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String modifyRole(
            @RequestParam(Keys.ROLENAME) String roleName,
            @RequestParam(Keys.ROLEAUTH) String roleAuth,
            @RequestParam(Keys.OPERATOR) String operator
    ){
        Role role = roleMapper.selectByRoleName(roleName);
        JSONObject result;
        if (role != null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.MODIFY_ROLE_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        Role new_role = new Role();
        Date now = new Date();

        new_role.setRoleid(role.getRoleid());
        new_role.setOperator(operator);
        new_role.setRoleauth(roleAuth);
        new_role.setRolename(roleName);

        new_role.setRolemodify(simpleDateFormat.format(now));
        roleMapper.updateByPrimaryKey(new_role);


        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.MODIFY_ROLE_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteRole(
            @RequestParam(Keys.ID) String id
    ){
        Role user = roleMapper.selectByPrimaryKey(Integer.valueOf(id));
        JSONObject result ;
        if (user == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.DELETE_ROLE_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        roleMapper.deleteByPrimaryKey(Integer.valueOf(id));
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.DELETE_ROLE_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getRoleList(){
        List<Role> roles = roleMapper.selectAll();
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        JSONArray data = new JSONArray();
        for (Role role:roles){
            JSONObject every_role = new JSONObject();
            every_role.put(Keys.ROLENAME,role.getRoleauth());
            every_role.put(Keys.ROLEAUTH,role.getRoleauth());
            every_role.put(Keys.ROLEID,role.getRoleid());
            every_role.put(Keys.ROLECREATE, role.getRolecreate());
            every_role.put(Keys.ROLEMODIFY,role.getRolemodify());
            every_role.put(Keys.OPERATOR,role.getOperator());
            data.add(every_role);
        }
        result.put(Keys.MSG,"");
        result.put(Keys.DATA,data);
        return result.toJSONString();
    }

    @RequestMapping(value = "/manualInput", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteRole(
            @RequestParam(Keys.TITLE) String title,
            @RequestParam(Keys.TITLE1) String title1,
            @RequestParam(Keys.TITLE2) String title2,
            @RequestParam(Keys.TITLE3) String title3,
            @RequestParam(Keys.TITLE4) String title4,
            @RequestParam(Keys.AUTHOR1) String author1,
            @RequestParam(Keys.AUTHOR2) String author2,
            @RequestParam(Keys.AUTHOR3) String author3,
            @RequestParam(Keys.AUTHOR4) String author4,
            @RequestParam(Keys.ABSTRACT1) String abstract1,
            @RequestParam(Keys.ABSTRACT2) String abstract2,
            @RequestParam(Keys.ABSTRACT3) String abstract3,
            @RequestParam(Keys.ABSTRACT4) String abstract4,
            @RequestParam(Keys.JOURNAL1) String journal1,
            @RequestParam(Keys.JOURNAL2) String journal2,
            @RequestParam(Keys.JOURNAL3) String journal3,
            @RequestParam(Keys.JOURNAL4) String journal4,
            @RequestParam(Keys.PUBMED1) String pubmed1,
            @RequestParam(Keys.PUBMED2) String pubmed2,
            @RequestParam(Keys.PUBMED3) String pubmed3,
            @RequestParam(Keys.PUBMED4) String pubmed4,
            @RequestParam(Keys.LOCUS) String locus,
            @RequestParam(Keys.PDBID) String pdbid,
            @RequestParam(Keys.DBSOURCE) String dbsource,
            @RequestParam(Keys.SOURCE) String source,
            @RequestParam(Keys.ORGANISM) String organsim,
            @RequestParam(Keys.DATE) String date,
            @RequestParam(Keys.COUNTRY) String country,
            @RequestParam(Keys.ORIGIN) String origin,
            @RequestParam(Keys.OPERATOR) String operator

    ){
        JSONObject result ;
        Mme mme = new Mme();
        mme.setAbstract1(abstract1);
        mme.setAbstract2(abstract2);
        mme.setAbstract3(abstract3);
        mme.setAbstract4(abstract4);
        mme.setTitle(title);
        mme.setTitle1(title1);
        mme.setTitle2(title2);
        mme.setTitle3(title3);
        mme.setTitle4(title4);
        mme.setJournal1(journal1);
        mme.setJournal2(journal2);
        mme.setJournal3(journal3);
        mme.setJournal4(journal4);
        mme.setPubmed1(pubmed1);
        mme.setPubmed2(pubmed2);
        mme.setPubmed3(pubmed3);
        mme.setPubmed4(pubmed4);
        mme.setAuthor1(author1);
        mme.setAuthor2(author2);
        mme.setAuthor3(author3);
        mme.setAuthor4(author4);
        mme.setLocus(locus);
        mme.setOrganism(organsim);
        mme.setOrigin(origin);
        mme.setPdbid(pdbid);
        mme.setDate(date);
        mme.setDbsource(dbsource);
        mme.setSource(source);
        mme.setCountry(country);
        mme.setOperator(operator);
        mme.setOperatedate(simpleDateFormat.format(new Date()));
        if (mmeMapper.insert(mme) > 0){
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.MIFSUCCESS);
            result.put(Keys.DATA,new JSONObject());
        }else {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.MIFAILD);
            result.put(Keys.DATA,new JSONObject());
        }
        return result.toJSONString();
    }

}
