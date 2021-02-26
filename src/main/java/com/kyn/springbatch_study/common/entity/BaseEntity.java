package com.kyn.springbatch_study.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @JSONField(format = "yyyyMMddHHmmss")
    @CreatedDate
    protected Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    @JSONField(format = "yyyyMMddHHmmss")
    @LastModifiedDate
    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}