package com.assignment.keyvaluestore.udaan.model;

import javax.persistence.*;

@Entity
@Table(name="transaction_settlement")
// indicates the transaction is complete recorded in the system
public class TransactionSettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="desc")
    private String desc;

    @Column(name="from_user_id")
    private Integer fromId;

    @Column(name="to_user_id")
    private Integer toId;

    @Column(name="amount")
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionSettlement() {
    }

    public TransactionSettlement(Integer id, String desc, Integer fromId, Integer toId, Double amount) {
        this.id = id;
        this.desc = desc;
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }
}
