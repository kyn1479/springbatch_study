package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.kyn.springbatch_study.common.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author Kangyanan
 * @Description: 批次表
 * @date 2021/2/26
 */
@Entity
@Table(name = "batch_info")
public class BatchInfo extends BaseEntity {
    /** 主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "transCode")
    private String transCode;

    @Column(name = "trans_date")
    @JSONField(format = "yyyyMMdd")
    private Date transDate;

    @Column(name = "trans_date_time")
    @JSONField(format = "yyyyMMddHHmmss")
    private Date transDateTime;

    @Column(name = "batch_status")
    private String batchStatus;

    @Column(name = "inst_code")
    private String instCode;

    @Column(name = "inst_resp_code")
    private String instRespCode;

    @Column(name = "inst_resp_msg")
    private String instRespMsg;

    @Column(name = "inst_req_no")
    private String instReqNo;

    @Column(name = "inst_resp_no")
    private String instRespNo;

    @Column(name = "total_num")
    private Long totalNum;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "total_suc_num")
    private Long totalSucNum;

    @Column(name = "total_suc_amount")
    private BigDecimal totalSucAmount;

    @Column(name = "acct_no")
    private String acctNo;

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

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
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

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getInstRespCode() {
        return instRespCode;
    }

    public void setInstRespCode(String instRespCode) {
        this.instRespCode = instRespCode;
    }

    public String getInstRespMsg() {
        return instRespMsg;
    }

    public void setInstRespMsg(String instRespMsg) {
        this.instRespMsg = instRespMsg;
    }

    public String getInstReqNo() {
        return instReqNo;
    }

    public void setInstReqNo(String instReqNo) {
        this.instReqNo = instReqNo;
    }

    public String getInstRespNo() {
        return instRespNo;
    }

    public void setInstRespNo(String instRespNo) {
        this.instRespNo = instRespNo;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalSucNum() {
        return totalSucNum;
    }

    public void setTotalSucNum(Long totalSucNum) {
        this.totalSucNum = totalSucNum;
    }

    public BigDecimal getTotalSucAmount() {
        return totalSucAmount;
    }

    public void setTotalSucAmount(BigDecimal totalSucAmount) {
        this.totalSucAmount = totalSucAmount;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    @Override
    public String toString() {
        return "BatchInfo{" +
                "id=" + id +
                ", batchNo='" + batchNo + '\'' +
                ", transCode='" + transCode + '\'' +
                ", transDate=" + transDate +
                ", transDateTime=" + transDateTime +
                ", batchStatus='" + batchStatus + '\'' +
                ", instCode='" + instCode + '\'' +
                ", instRespCode='" + instRespCode + '\'' +
                ", instRespMsg='" + instRespMsg + '\'' +
                ", instReqNo='" + instReqNo + '\'' +
                ", instRespNo='" + instRespNo + '\'' +
                ", totalNum=" + totalNum +
                ", totalAmount=" + totalAmount +
                ", totalSucNum=" + totalSucNum +
                ", totalSucAmount=" + totalSucAmount +
                ", acctNo='" + acctNo + '\'' +
                '}';
    }
}