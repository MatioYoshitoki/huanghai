package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.DefaultValue;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.MmeMapper;
import com.matio.mapping.RoleMapper;
import com.matio.mapping.UserMapper;
import com.matio.pojo.*;
import com.matio.unit.JsonUtil;
import com.matio.unit.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
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


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String login(
            @RequestParam(Keys.ACCOUNT) String account,
            @RequestParam(Keys.PASSWORD) String password
    ) {
        JSONObject result;
        try {
            View_user user = userMapper.selectByAccount(account);
            if (user != null && user.getPassword().equals(MD5Util.encode32(password))) {
                JSONObject data = new JSONObject();
                data.put(Keys.USERACCOUNT, user.getUseraccount());
                data.put(Keys.USERGENDER, user.getUsergender());
                data.put(Keys.USERCREATE, user.getUsercreate());
                data.put(Keys.USERMODIFY, user.getUsermodify());
                data.put(Keys.ROLEAUTH, user.getRoleauth());
                try {
                    data.put(Keys.USERNAME, new String(user.getUsername(), "utf8"));
                    data.put(Keys.ROLENAME, new String(user.getRolename(), "utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.MSG, "");
                result.put(Keys.DATA, data);
            } else {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.NO_SUCH_NAME_WRONG_PASSWORD);
                result.put(Keys.DATA, new JSONObject());
            }
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.SYSTEM_ERROR);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String regist(
            @RequestParam(Keys.USERACCOUNT) String userAccount,
            @RequestParam(Keys.USERNAME) String userName,
            @RequestParam(Keys.USERGENDER) String userGender,
            @RequestParam(Keys.USERROLE) String userRole,
            @RequestParam(Keys.OPERATOR) String operator
    ) {
        JSONObject result;
        try {
            Date now = new Date();
            User new_user = new User();
            try {
                new_user.setOperator(operator.getBytes("utf8"));
                new_user.setUsername(userName.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            new_user.setUseraccount(userAccount);
            new_user.setUsergender(userGender);
            new_user.setUserrole(Integer.valueOf(userRole));
            new_user.setPassword(MD5Util.encode32(DefaultValue.DEFAULT_PASSWORD));
            new_user.setUsercreate(simpleDateFormat.format(now));
            new_user.setUsermodify(simpleDateFormat.format(now));
            if(userMapper.insert(new_user) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.NEW_CREATE_FAILD);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG, Errors.NEW_CREATE_SUCCESS);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.NEW_CREATE_FAILD);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String modify(
            @RequestParam(Keys.USERACCOUNT) String userAccount,
            @RequestParam(Keys.USERNAME) String userName,
            @RequestParam(Keys.USERGENDER) String userGender,
            @RequestParam(Keys.USERROLE) String userRole,
            @RequestParam(Keys.OPERATOR) String operator
    ) {
        JSONObject result;
        try {
            Date now = new Date();
            User modify_user = new User();
            try {
                modify_user.setOperator(operator.getBytes("utf8"));
                modify_user.setUsername(userName.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            modify_user.setUseraccount(userAccount);
            modify_user.setUsergender(userGender);
            modify_user.setUserrole(Integer.valueOf(userRole));
            modify_user.setUsermodify(simpleDateFormat.format(now));
            if(userMapper.updateByAccount(modify_user) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.MODIFY_FAILD);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG, Errors.MODIFY_SUCCESS);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.SYSTEM_ERROR);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String deleteUser(
            @RequestParam(Keys.USERACCOUNT) String account
    ) {
        JSONObject result;
        try {
            if(userMapper.deleteByAccount(account) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.DELETE_FAILE);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.SYSTEM_ERROR);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG, Errors.DELETE_SUCCESS);
        result.put(Keys.DATA, new JSONObject());
        return result.toJSONString();
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getUserList(
            @RequestParam(Keys.TYPE) String type,
            @RequestParam(Keys.STARTDATE) String startDate,
            @RequestParam(Keys.ENDDATE) String endDate,
            @RequestParam(Keys.NUMBERPERPAGE) String numberPerPage,
            @RequestParam(Keys.STARTPOS) String startPos,
            @RequestParam(Keys.PARAM) String param
    ) {
        JSONObject result;
        JSONArray data = new JSONArray();
        int size = 0;
        try {
            UserCondition condition = new UserCondition();
            int startPos_int = Integer.valueOf(startPos);
            int numberPerPage_int = Integer.valueOf(numberPerPage);
            int startSize = (startPos_int - 1) * numberPerPage_int;
            int endSize = (startPos_int) * numberPerPage_int ;
            condition.setStartSize(startSize);;
            condition.setEndSize(endSize);
            switch (type){
                case "0":
                    break;
                case "1":
                    condition.setUserAccount(param);
                    break;
                case "2":
                    try {
                        condition.setUserName(param.getBytes("utf8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        condition.setRoleName(param.getBytes("utf8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    startDate += " 00:00:00";
                    endDate += " 23:59:59";
                    condition.setStartDate_op(startDate);
                    condition.setEndDate_op(endDate);
                    break;
                case "5":
                    startDate += " 00:00:00";
                    endDate += " 23:59:59";
                    condition.setStartDate_md(startDate);
                    condition.setEndDate_md(endDate);
                    break;
                default:
                    return "";
            }
            List<View_user> users = userMapper.selectByCondition(condition);
            for (View_user user : users) {
                JSONObject every_user = new JSONObject();
                every_user.put(Keys.USERACCOUNT, user.getUseraccount());
                try {
                    every_user.put(Keys.USERNAME, new String(user.getUsername(), "utf8"));
                    every_user.put(Keys.ROLENAME, new String(user.getRolename(), "utf8"));
                    every_user.put(Keys.OPERATOR, new String(user.getOperator(), "utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                every_user.put(Keys.USERGENDER, user.getUsergender());
                every_user.put(Keys.USERROLE, user.getUserrole());
                every_user.put(Keys.USERCREATE, user.getUsercreate());
                every_user.put(Keys.USERMODIFY, user.getUsermodify());
                data.add(every_user);
            }
            size = userMapper.selectCountByCondition(condition);
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.SYSTEM_ERROR);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG, "");
        result.put(Keys.DATA, data);
        result.put(Keys.COUNT, size);
        return result.toJSONString();
    }

    @RequestMapping(value = "/createRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String createRole(
            @RequestParam(Keys.ROLENAME) String roleName,
            @RequestParam(Keys.ROLEAUTH) String roleAuth,
            @RequestParam(Keys.OPERATOR) String operator
    ) {
        JSONObject result;
        try {
            Role new_role = new Role();
            Date now = new Date();
            try {
                new_role.setOperator(operator.getBytes("utf8"));
                new_role.setRolename(roleName.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            new_role.setRoleauth(roleAuth);
            new_role.setRolecreate(simpleDateFormat.format(now));
            new_role.setRolemodify(simpleDateFormat.format(now));
            if(roleMapper.insert(new_role) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.NEW_ROLE_FAILD);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG, Errors.NEW_ROLE_SUCCESS);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.NEW_ROLE_FAILD);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/modifyRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String modifyRole(
            @RequestParam(Keys.ROLEID) String roleId,
            @RequestParam(Keys.ROLENAME) String roleName,
            @RequestParam(Keys.ROLEAUTH) String roleAuth,
            @RequestParam(Keys.OPERATOR) String operator
    ) {
        JSONObject result;
        try {
            Role new_role = new Role();
            Date now = new Date();
            try {
                new_role.setOperator(operator.getBytes("utf8"));
                new_role.setRolename(roleName.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            new_role.setRoleid(Integer.parseInt(roleId));
            new_role.setRoleauth(roleAuth);
            new_role.setRolemodify(simpleDateFormat.format(now));
            if(roleMapper.updateByPrimaryKey(new_role) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.MODIFY_ROLE_FAILD);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG, Errors.MODIFY_ROLE_SUCCESS);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.MODIFY_ROLE_FAILD);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String deleteRole(
            @RequestParam(Keys.ID) String id
    ) {
        JSONObject result;
        try {
            if(roleMapper.deleteByPrimaryKey(Integer.valueOf(id)) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.DELETE_ROLE_FAILD);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG, Errors.DELETE_ROLE_SUCCESS);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.DELETE_ROLE_FOREIGN);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getRoleList(
            @RequestParam(Keys.NUMBERPERPAGE) String numberPerPage,
            @RequestParam(Keys.STARTPOS) String startPos
    ) {
        UserCondition userCondition = new UserCondition();
        int startPos_int = Integer.valueOf(startPos);
        int numberPerPage_int = Integer.valueOf(numberPerPage);
        int startSize = (startPos_int - 1) * numberPerPage_int;
        int endSize = (startPos_int) * numberPerPage_int;
        userCondition.setStartSize(startSize);
        userCondition.setEndSize(endSize);
        JSONObject result;
        JSONArray data = new JSONArray();
        int count = 0;
        try {
            List<Role> roles = roleMapper.selectAll(userCondition);
            for (Role role : roles) {
                JSONObject every_role = new JSONObject();
                try {
                    every_role.put(Keys.ROLENAME, new String(role.getRolename(), "utf8"));
                    every_role.put(Keys.OPERATOR, new String(role.getOperator(), "utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                every_role.put(Keys.ROLEAUTH, role.getRoleauth());
                every_role.put(Keys.ROLEID, role.getRoleid());
                every_role.put(Keys.ROLECREATE, role.getRolecreate());
                every_role.put(Keys.ROLEMODIFY, role.getRolemodify());
                data.add(every_role);
            }
            count = roleMapper.selectCountAll();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.DELETE_ROLE_FOREIGN);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG, "");
        result.put(Keys.DATA, data);
        result.put(Keys.COUNT, count);
        return result.toJSONString();
    }

    @RequestMapping(value = "/getRoleSelect", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String getRoleSelect() {
        JSONObject result;
        JSONArray data = new JSONArray();
        try {
            List<Role> roles = roleMapper.selectRole();
            for (Role role : roles) {
                JSONObject every_role = new JSONObject();
                try {
                    every_role.put(Keys.ROLENAME, new String(role.getRolename(), "utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                every_role.put(Keys.ROLEID, role.getRoleid());
                data.add(every_role);
            }
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.DELETE_ROLE_FOREIGN);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG, "");
        result.put(Keys.DATA, data);
        return result.toJSONString();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String changePassword(
            @RequestParam(Keys.ACCOUNT) String account,
            @RequestParam(Keys.OLDPWD) String oldPwd,
            @RequestParam(Keys.NEWPWD) String newPwd
    ) {
        JSONObject result;
        try {
            View_user user = userMapper.selectByAccount(account);
            if (user != null && user.getPassword().equals(MD5Util.encode32(oldPwd))) {
                User changePWD = new User();
                changePWD.setUseraccount(user.getUseraccount());
                changePWD.setPassword(MD5Util.encode32(newPwd));
                if(userMapper.updateByAccount(changePWD) <= 0) {
                    result = JsonUtil.fromErrors(Errors.FAILD);
                    result.put(Keys.MSG, Errors.CHANGE_PASSWORD_FAILD);
                    result.put(Keys.DATA, new JSONObject());
                    return result.toJSONString();
                }
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.MSG, Errors.CHANGE_PASSWORD_SUCCESS);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            } else {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG, Errors.CHANGE_PASSWORD_NOMATCH);
                result.put(Keys.DATA, new JSONObject());
                return result.toJSONString();
            }
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG, Errors.SYSTEM_ERROR);
            result.put(Keys.DATA, new JSONObject());
            return result.toJSONString();
        }
    }
}
