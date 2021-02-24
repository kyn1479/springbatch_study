package com.kyn.springbatch_study.hello_world.h_hello_world_item_writer;

/**
 * @author Kangyanan
 * @Description: 男孩表实体
 * @date 2021/2/19
 */

public class Boy {
    //主键
    private Long id;
    //姓名
    private String name;
    //性别 1：男 2：女
    private Byte sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
