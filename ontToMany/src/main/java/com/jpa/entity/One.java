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
@Table(name = "one")
@Data
public class One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    /**
     * 注意设置cascade时 一方 A 和多方 B 一定要对应, 如果要设置级联删除 则 A 和 B 都要设置CascadeType.REMOVE
     * 如果不设置级联, 则双方都不能设置 CascadeType.REMOVE  否则删除会出现问题
     */
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE,mappedBy = "one")
    private List<Many> manyList;


}
