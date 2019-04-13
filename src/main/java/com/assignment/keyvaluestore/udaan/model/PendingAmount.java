package com.assignment.keyvaluestore.udaan.model;

import javax.persistence.*;

@Entity
@Table(name="pending_amount")
// total pending amount tracker
public class PendingAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="from_user_id")
    private Integer fromUserId;

    @Column(name="to_user_id")
    private Integer toUserId;

    @Column(name="amount")
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PendingAmount() {
    }

    public PendingAmount(Integer fromUserId, Integer toUserId, Double amount) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
    }
}
