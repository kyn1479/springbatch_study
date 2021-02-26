package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.kyn.springbatch_study.common.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Kangyanan
 * @Description: 代扣交易信息表
 * @date 2021/2/20
 */
@Entity
@Table(name = "deduct_trans")
public class DeductTrans extends BaseEntity {
    /** 主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 批次号*/
    @Column(name = "batch_no")
    private String batchNo;

    /** 流水号*/
    @Column(name = "trans_no")
    private String transNo;

    /** 交易日期*/
    @Column(name = "trans_date")
    @JSONField(format = "yyyyMMdd")
    private Date transDate;

    /** 交易时间*/
    @Column(name = "trans_date_time")
    @JSONField(format = "yyyyMMddHHmmss")
    private Date transDateTime;

    /** 交易金额*/
    @Column(name = "trans_amount")
    private BigDecimal transAmount;

    /** 交易状态*/
    @Column(name = "trans_status")
    private String transStatus;

    /** 渠道编码*/
    @Column(name = "inst_code")
    private String instCode;

    /** 交易账号*/
    @Column(name = "acct_no")
    private String acctNo;

    /** 业务状态*/
    @Column(name = "process_status")
    private String processStatus;

    /** 请求渠道流水*/
    @Column(name = "inst_req_no")
    private String instReqNo;

    /** 交易类型*/
    @Column(name = "trans_type")
    private String transType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Date getTransDateTime() {
        return transDateTime;
    }

    public void setTransDateTime(Date transDateTime) {
        this.transDateTime = transDateTime;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getInstReqNo() {
        return instReqNo;
    }

    public void setInstReqNo(String instReqNo) {
        this.instReqNo = instReqNo;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Override
    public String toString() {
        return "DeductTrans{" +
                "id=" + id +
                ", batchNo='" + batchNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", transDate=" + transDate +
                ", transDateTime=" + transDateTime +
                ", transAmount=" + transAmount +
                ", transStatus='" + transStatus + '\'' +
                ", instCode='" + instCode + '\'' +
                ", acctNo='" + acctNo + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", instReqNo='" + instReqNo + '\'' +
                ", transType='" + transType + '\'' +
                '}';
    }
}
