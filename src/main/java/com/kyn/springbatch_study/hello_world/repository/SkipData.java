package com.kyn.springbatch_study.hello_world.repository;

import javax.persistence.*;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/25
 */
@Entity
@Table(name = "skipbills")
public class SkipData {
    /** 主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "line")
    private int lineNum;

    @Column(name = "content")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
