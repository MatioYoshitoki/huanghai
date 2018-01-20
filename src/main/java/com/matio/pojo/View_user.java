package com.matio.pojo;

public class View_user {
    private String useraccount;

    private Integer userrole;

    private String password;

    private String usergender;

    private String usercreate;

    private String usermodify;

    private byte[] username;

    private byte[] operator;

    private byte[] rolename;

    private String roleauth;

    public String getRoleauth() {
        return roleauth;
    }

    public void setRoleauth(String roleauth) {
        this.roleauth = roleauth;
    }

    public byte[] getRolename() {
        return rolename;
    }

    public void setRolename(byte[] rolename) {
        this.rolename = rolename;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public Integer getUserrole() {
        return userrole;
    }

    public void setUserrole(Integer userrole) {
        this.userrole = userrole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsergender() {
        return usergender;
    }

    public void setUsergender(String usergender) {
        this.usergender = usergender;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public String getUsermodify() {
        return usermodify;
    }

    public void setUsermodify(String usermodify) {
        this.usermodify = usermodify;
    }

    public byte[] getUsername() {
        return username;
    }

    public void setUsername(byte[] username) {
        this.username = username;
    }

    public byte[] getOperator() {
        return operator;
    }

    public void setOperator(byte[] operator) {
        this.operator = operator;
    }
}