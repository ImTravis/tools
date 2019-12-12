package com.tools.redis.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xcc.
 * @data 2019/2/1.
 * @time 11:55.
 * 对账的 数据的 数据格式
 */
@Data
public class CheckDataInfo implements Serializable {

    private Date orderTime;//下单时间 同一天的订单

    private BigDecimal payment;//交易额

    private int orderStaus;//订单状态 只对 1,2,

    private String orderNo;//订单号  同一款订单的交易号一样

    @Override
    public String toString() {
        return "CheckDataInfo{" +
                "orderTime=" + orderTime +
                ", payment=" + payment +
                ", orderStaus=" + orderStaus +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public int getOrderStaus() {
        return orderStaus;
    }

    public void setOrderStaus(int orderStaus) {
        this.orderStaus = orderStaus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
