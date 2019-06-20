package com.jpa;

import com.jpa.entity.Many;
import com.jpa.entity.One;
import com.jpa.repository.ManyRepository;
import com.jpa.repository.OneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTests {

    @Autowired
    private ManyRepository manyRepository;

    @Autowired
    private OneRepository oneRepository;

    /**
     * 保存放弃维护外键一方时, 多方的实体会保存, 但是不会保存外键;
     */
    @Test
    public void test1() {
        Many many1 = new Many();
        many1.setDescription("many1");

        Many many2 = new Many();
        many2.setDescription("many2");

        One one = new One();
        one.setDescription("one");

        List<Many> manyList = new ArrayList<Many>();
        manyList.add(many1);
        manyList.add(many2);

        one.setManyList(manyList);
        oneRepository.save(one);

    }

    @Test
    public void test2() {
        One one = oneRepository.findAll().stream().findAny().get();
        System.out.println("");

    }

    /**
     * 多的一方放弃维护外键, 所以,即使list中去除一个entity实体,该实体的外键也不会更新,
     * 也就是说不能通过重新保存多方实体的方式去除一方与多方的关系
     * 但是修改多方的非外键字段时, 还是会保存的
     */
    @Test
    public void test3() {
        One one = oneRepository.findAll().stream().findAny().get();
        Many remove = one.getManyList().remove(0);
        Many many = one.getManyList().stream().findAny().get();
        many.setDescription("aha");
        oneRepository.save(one);
    }

    /**
     * 级联删除会删除一方与多方
     */
    @Test
    public void test4() {
        One one = oneRepository.findAll().stream().findAny().get();
        oneRepository.delete(one);
    }

    @Test
    public void test5() {
        One one = new One();
        one.setDescription("one");
        oneRepository.save(one);

        One one1 = oneRepository.findAll().stream().findAny().get();

        Many many1 = new Many();
        many1.setDescription("many1");
        many1.setOne(one1);

        manyRepository.save(many1);

        Many many2 = new Many();
        many2.setDescription("many2");
        many2.setOne(one1);

        manyRepository.save(many2);
    }

    @Test
    public void test6() {
        Many many = manyRepository.findAll().stream().findAny().get();
        manyRepository.delete(many);
    }

    @Test
    public void test7() {
        Many many = manyRepository.findAll().stream().findAny().get();
        many.getOne().setDescription("呵呵");
        manyRepository.save(many);
    }

}
