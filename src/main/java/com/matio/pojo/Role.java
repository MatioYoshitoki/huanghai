package com.matio.pojo;

public class Role {
    private Integer roleid;

    private String rolename;

    private String roleauth;

    private String rolecreate;

    private String rolemodify;

    private String operator;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleauth() {
        return roleauth;
    }

    public void setRoleauth(String roleauth) {
        this.roleauth = roleauth;
    }

    public String getRolecreate() {
        return rolecreate;
    }

    public void setRolecreate(String rolecreate) {
        this.rolecreate = rolecreate;
    }

    public String getRolemodify() {
        return rolemodify;
    }

    public void setRolemodify(String rolemodify) {
        this.rolemodify = rolemodify;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}