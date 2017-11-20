package com.matio.pojo;

/**
 * Created by matioyoshitoki on 2017/11/20.
 */
public class MmeCondition {
    private String startDate_op ;
    private String endDate_op ;
    private String startDate_md ;
    private String endDate_md ;
    private Integer startSize ;
    private Integer endSize ;
    private String pdbId ;
    private String type ;
    private String ec2 ;
    private String operator ;
    private String modifier ;
    public void setEc2(String ec2) {
        this.ec2 = ec2;
    }


    public void setEndSize(Integer endSize) {
        this.endSize = endSize;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setPdbId(String pdbId) {
        this.pdbId = pdbId;
    }


    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEc2() {
        return ec2;
    }


    public Integer getEndSize() {
        return endSize;
    }

    public Integer getStartSize() {
        return startSize;
    }

    public String getOperator() {
        return operator;
    }

    public String getPdbId() {
        return pdbId;
    }


    public String getType() {
        return type;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setEndDate_op(String endDate_op) {
        this.endDate_op = endDate_op;
    }

    public void setStartDate_op(String startDate_op) {
        this.startDate_op = startDate_op;
    }

    public String getEndDate_op() {
        return endDate_op;
    }

    public String getStartDate_op() {
        return startDate_op;
    }

    public void setEndDate_md(String endDate_md) {
        this.endDate_md = endDate_md;
    }

    public String getEndDate_md() {
        return endDate_md;
    }

    public void setStartDate_md(String startDate_md) {
        this.startDate_md = startDate_md;
    }

    public String getStartDate_md() {
        return startDate_md;
    }
}
