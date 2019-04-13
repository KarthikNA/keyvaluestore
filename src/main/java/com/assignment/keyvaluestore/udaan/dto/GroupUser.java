package com.assignment.keyvaluestore.udaan.dto;

//specifies individually how much people owe
public class GroupUser {
    private Integer userId;
    private Double percentageShare;
    private Double amount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPercentageShare() {
        return percentageShare;
    }

    public void setPercentageShare(Double percentageShare) {
        this.percentageShare = percentageShare;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public GroupUser() {
    }

    public GroupUser(Integer userId, Double percentageShare, Double amount) {
        this.userId = userId;
        this.percentageShare = percentageShare;
        this.amount = amount;
    }
}
