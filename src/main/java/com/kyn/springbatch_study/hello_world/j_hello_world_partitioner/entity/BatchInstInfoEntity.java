package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.entity;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
public class BatchInstInfoEntity {

    /** 机构码*/
    private String instCode;

    /** 拆分数量*/
    private int splitSum;

    /** 渠道账号*/
    private String instAcctNo;

    public BatchInstInfoEntity(String instCode, int splitSum, String instAcctNo) {
        this.instCode = instCode;
        this.splitSum = splitSum;
        this.instAcctNo = instAcctNo;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public int getSplitSum() {
        return splitSum;
    }

    public void setSplitSum(int splitSum) {
        this.splitSum = splitSum;
    }

    public String getInstAcctNo() {
        return instAcctNo;
    }

    public void setInstAcctNo(String instAcctNo) {
        this.instAcctNo = instAcctNo;
    }
}
