package com.jpa;

import com.jpa.entity.ManyA;
import com.jpa.entity.ManyB;
import com.jpa.repository.ManyARepository;
import com.jpa.repository.ManyBRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTests {

    @Autowired
    private ManyARepository manyARepository;

    @Autowired
    private ManyBRepository manyBRepository;

    @Test
    public void test1(){
        ManyA manyA1 = new ManyA();
        ManyA manyA2 = new ManyA();

        ManyB manyB1 = new ManyB();
        ManyB manyB2 = new ManyB();
        ManyB manyB3 = new ManyB();

        manyA1.setDescription("manya1");
        manyA2.setDescription("manya2");

        manyB1.setDescription("manyb1");
        manyB2.setDescription("manyb2");
        manyB3.setDescription("manyb3");

        List<ManyA> manyAList = new ArrayList<ManyA>();
        List<ManyB> manyBList = new ArrayList<ManyB>();

        manyAList.add(manyA1);
        manyAList.add(manyA2);

        manyBList.add(manyB1);
        manyBList.add(manyB2);
        manyBList.add(manyB3);

        manyA1.setManyBList(manyBList);
        manyA2.setManyBList(manyBList);

        manyARepository.save(manyAList);
    }

    /**
     * manyA 不维护外键, 所以必须要设置 级联删除才能删除成功
     * 否则会报外键约束
     *
     */
    @Test
    public void test2(){
        ManyA manyA = manyARepository.findAll().stream().findAny().get();
        manyARepository.delete(manyA);
    }

    /**
     * 正确的删除方式是 通过维护外键的一方 去除外键关系, 再删除
     */
    @Test
    public void test3(){
        ManyA manyA = manyARepository.findAll().stream().findAny().get();

        List<ManyB> manyBList = manyA.getManyBList();
        manyBList.forEach(
                x -> x.getManyAList().remove(manyA)
        );
        manyBRepository.save(manyBList);

        manyARepository.delete(manyA);
    }

    @Test
    public void test4(){
        ManyA manyA = manyARepository.findAll().stream().findAny().get();
        List<ManyB> manyBList = manyA.getManyBList();
        if(!CollectionUtils.isEmpty(manyBList)){
            ManyB manyB = manyBList.stream().findAny().get();
            manyB.setDescription("update");
        }
        manyARepository.save(manyA);
    }

    @Test
    public void test5(){
        ManyA manyA = manyARepository.findAll().stream().findAny().get();
        System.out.println("manyA");
    }

    @Test
    public void test6(){
        ManyA manyA1 = new ManyA();
        ManyA manyA2 = new ManyA();

        ManyB manyB1 = new ManyB();
        ManyB manyB2 = new ManyB();
        ManyB manyB3 = new ManyB();

        manyA1.setDescription("manya1");
        manyA2.setDescription("manya2");

        manyB1.setDescription("manyb1");
        manyB2.setDescription("manyb2");
        manyB3.setDescription("manyb3");

        List<ManyA> manyAList = new ArrayList<ManyA>();
        List<ManyB> manyBList = new ArrayList<ManyB>();

        manyAList.add(manyA1);
        manyAList.add(manyA2);

        manyBList.add(manyB1);
        manyBList.add(manyB2);
        manyBList.add(manyB3);

        manyB1.setManyAList(manyAList);
        manyB2.setManyAList(manyAList);
        manyB3.setManyAList(manyAList);

        manyBRepository.save(manyBList);
    }

    /**
     * 维护关系的一方 B 如果设置级联删除, 会删除相关的 A 如果不是级联删除, 则会删除B 和中间表
     */
    @Test
    public void test7(){
        ManyB manyB = manyBRepository.findAll().stream().findAny().get();
        manyBRepository.delete(manyB);
    }

}
