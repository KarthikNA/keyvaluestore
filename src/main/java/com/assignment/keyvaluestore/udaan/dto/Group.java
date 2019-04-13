package com.assignment.keyvaluestore.udaan.dto;

import com.assignment.keyvaluestore.udaan.dto.GroupUser;
import java.util.List;

//new expense claim dto, one to one or group
public class Group {
    private Byte splitType;
    private Double amount;
    private List<GroupUser> groupUserList;


    public Group(Double amount, List<GroupUser> groupUserList) {
        this.amount = amount;
        this.groupUserList = groupUserList;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<GroupUser> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(List<GroupUser> groupUserList) {
        this.groupUserList = groupUserList;
    }

    public Group() {
    }
}
