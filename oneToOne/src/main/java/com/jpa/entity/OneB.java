package com.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2019/6/19 14:21
 */
@Entity
@Table(name = "one_b")
@Data
public class OneB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "one_a_id",referencedColumnName="id")
    private OneA oneA;
}
