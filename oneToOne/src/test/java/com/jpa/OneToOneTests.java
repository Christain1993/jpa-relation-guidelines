package com.jpa;

import com.jpa.entity.OneA;
import com.jpa.entity.OneB;
import com.jpa.repository.OneARepository;
import com.jpa.repository.OneBRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToOneTests {

    @Autowired
    private OneARepository oneARepository;
    @Autowired
    private OneBRepository oneBRepository;

    /**
     * 保存onea 将会保存oneb 但是不会保存oneb上的外键,
     * 所以保存实体时, 要保存维护外键的实体
     */
    @Test
    public void test1() {
        OneA oneA = new OneA();
        oneA.setDescription("oneA");

        OneB oneB = new OneB();
        oneB.setDescription("oneB");
        oneB.setOneA(oneA);

        oneA.setOneB(oneB);

        oneARepository.save(oneA);

    }

    /**
     * 查询时会级联查询
     */
    @Test
    public void test2() {
        OneA oneA = oneARepository.findAll().stream().findAny().get();
    }

    /**
     * 更新时会级联更新
     */
    @Test
    public void test3() {
        OneA oneA = oneARepository.findAll().stream().findAny().get();
        oneA.getOneB().setDescription("update");
        oneARepository.save(oneA);
    }

    /**
     * 删除时会级联删除
     */
    @Test
    public void test4() {
        OneA oneA = oneARepository.findAll().stream().findAny().get();
        oneARepository.delete(oneA);
    }

    /**
     * 保存维护外键的一方, 存数据库时会保存两个实体以及外键,
     * 推荐此方法保存
     */
    @Test
    public void test5() {
        OneB oneB = new OneB();
        oneB.setDescription("oneB");

        OneA oneA = new OneA();
        oneA.setDescription("oneA");

        oneB.setOneA(oneA);

        oneBRepository.save(oneB);

    }






}
