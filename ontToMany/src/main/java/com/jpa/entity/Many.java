package com.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2019/6/19 16:13
 */
@Entity
@Table(name = "many")
@Data
public class Many {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    /**
     * 级联的type如果包含PERSIST的话, 可能会报此异常 PersistentObjectException: detached entity passed to persist: com.jpa.entity.One
     * 原因是 一个多方 A 级联一个一方 B ,此时另一个多方 C 级联保存 B 时 B已经有了Id 便不允许保存 , 将级联cascade 改成merge
     * 然后先保存 一方 B 在分别保存 A C 解决此问题
     */

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "one_id",referencedColumnName = "id")
    private One one ;
}
