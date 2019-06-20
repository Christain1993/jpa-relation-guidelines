package com.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2019/6/19 14:21
 */
@Entity
@Table(name = "many_a")
@Data
public class ManyA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    /**
     * 此实体 A 是不维护外键的一方,不级联删除 CascadeType.REMOVE 时, 删除操作会失败,提示外键约束错误 ,原因就在于无法控制外键
     * 如果设置了级联删除,会删除另一实体 B 如果 B 设置了 CascadeType.REMOVE 那么将会删除所有关联的数据, 有可能将表删的干干净净 ,
     *                                      如果 B 没有设置 CascadeType.REMOVE 那么将会删除 实体 B 和中间表
     */
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "manyAList",fetch = FetchType.EAGER)
    private List<ManyB> manyBList;
}
