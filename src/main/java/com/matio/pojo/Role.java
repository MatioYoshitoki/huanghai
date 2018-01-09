package com.matio.pojo;

public class Role {
    private Integer roleid;

    private String roleauth;

    private String rolecreate;

    private String rolemodify;

    private byte[] rolename;

    private byte[] operator;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

    public byte[] getRolename() {
        return rolename;
    }

    public void setRolename(byte[] rolename) {
        this.rolename = rolename;
    }

    public byte[] getOperator() {
        return operator;
    }

    public void setOperator(byte[] operator) {
        this.operator = operator;
    }
}