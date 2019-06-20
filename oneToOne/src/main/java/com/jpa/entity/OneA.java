package com.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2019/6/19 14:21
 */
@Entity
@Table(name = "one_a")
@Data
public class OneA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    /**
     * 放弃维护外键,保存实体时 会保存OneB的实体, 但是OneB上没有外键;
     * 更新和删除时 将会级联保存
     */
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "oneA")
    private OneB oneB;
}
