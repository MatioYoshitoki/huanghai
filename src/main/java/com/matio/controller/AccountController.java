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
        modify_user.setOperator(operator);
        modify_user.setUseraccount(userAccount);
        modify_user.setUsername(userName);
        modify_user.setUsergender(userGender);
        modify_user.setUserrole(Integer.valueOf(userRole));

        modify_user.setUsermodify(simpleDateFormat.format(now));
        userMapper.updateByAccount(modify_user);

        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.MODIFY_SUCCESS);
        result.put(Keys.DATA,new JSONObject());
        return result.toJSONString();

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteUser(
            @RequestParam(Keys.USERACCOUNT) String account
    ){
        User user = userMapper.selectByAccount(account);
        JSONObject result ;
        if (user == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.DELETE_FAILE);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        userMapper.deleteByAccount(account);
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
            every_user.put(Keys.USERACCOUNT,user.getUseraccount());
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
        if (role != null){
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
            @RequestParam(Keys.ROLEID) String roleId,
            @RequestParam(Keys.ROLENAME) String roleName,
            @RequestParam(Keys.ROLEAUTH) String roleAuth,
            @RequestParam(Keys.OPERATOR) String operator
    ){
        Role role = roleMapper.selectByPrimaryKey(Integer.parseInt(roleId));
        JSONObject result;
        if (role == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.MODIFY_ROLE_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        Role new_role = new Role();
        Date now = new Date();

        new_role.setRoleid(Integer.parseInt(roleId));
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
            every_role.put(Keys.ROLENAME,role.getRolename());
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

    @RequestMapping(value = "/getRoleSelect", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getRoleSelect(){
        List<Role> roles = roleMapper.selectRole();
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        JSONArray data = new JSONArray();
        for (Role role:roles){
            JSONObject every_role = new JSONObject();
            every_role.put(Keys.ROLENAME,role.getRolename());
            every_role.put(Keys.ROLEID,role.getRoleid());
            data.add(every_role);
        }
        result.put(Keys.MSG,"");
        result.put(Keys.DATA,data);
        return result.toJSONString();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String changePassword(
            @RequestParam(Keys.ACCOUNT) String account,
            @RequestParam(Keys.OLDPWD) String oldPwd,
            @RequestParam(Keys.NEWPWD) String newPwd
    ) {
        User user = userMapper.selectByAccount(account);

        JSONObject result ;
        if (user!=null && user.getPassword().equals(MD5Util.encode32(oldPwd))){
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            User changePWD = new User();
            changePWD.setUseraccount(user.getUseraccount());
            changePWD.setPassword(newPwd);
            userMapper.updateByAccount(changePWD);
            result.put(Keys.MSG,Errors.CHANGE_PASSWORD_SUCCESS);
            result.put(Keys.DATA,new JSONObject());
        }else {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.CHANGE_PASSWORD_FAILD);
            result.put(Keys.DATA,new JSONObject());
        }

        return result.toJSONString();
    }
}
