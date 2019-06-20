package com.jpa.repository;

import com.jpa.entity.ManyA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2019/6/19 14:45
 */
@Repository
public interface ManyARepository extends JpaRepository<ManyA, Long>, JpaSpecificationExecutor<ManyA> {
}
