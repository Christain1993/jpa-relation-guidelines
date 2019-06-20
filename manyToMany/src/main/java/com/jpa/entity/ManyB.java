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
@Table(name = "many_b")
@Data
public class ManyB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(
            name = "r_manyA_manyB",
            joinColumns = @JoinColumn(name = "many_b_id"),
            inverseJoinColumns = @JoinColumn(name = "many_a_id")
    )
    private List<ManyA> manyAList;
}
