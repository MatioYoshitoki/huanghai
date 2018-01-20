package com.matio.pojo;

/**
 * Created by matioyoshitoki on 2017/11/20.
 */
public class UserCondition {
    private String startDate_op ;
    private String endDate_op ;
    private String startDate_md ;
    private String endDate_md ;
    private Integer startSize ;
    private Integer endSize ;
    private String userAccount ;
    private byte[] userName ;
    private byte[] roleName ;

    public void setStartDate_op(String startDate_op) {
        this.startDate_op = startDate_op;
    }

    public void setEndDate_op(String endDate_op) {
        this.endDate_op = endDate_op;
    }

    public void setStartDate_md(String startDate_md) {
        this.startDate_md = startDate_md;
    }

    public void setEndDate_md(String endDate_md) {
        this.endDate_md = endDate_md;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public void setEndSize(Integer endSize) {
        this.endSize = endSize;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public void setUserName(byte[] userName) {
        this.userName = userName;
    }

    public void setRoleName(byte[] roleName) {
        this.roleName = roleName;
    }

    public String getStartDate_op() {
        return startDate_op;
    }

    public String getEndDate_op() {
        return endDate_op;
    }

    public String getStartDate_md() {
        return startDate_md;
    }

    public String getEndDate_md() {
        return endDate_md;
    }

    public Integer getStartSize() {
        return startSize;
    }

    public Integer getEndSize() {
        return endSize;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public byte[] getUserName() {
        return userName;
    }

    public byte[] getRoleName() {
        return roleName;
    }
}
