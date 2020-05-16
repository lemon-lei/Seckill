package com.lei.dao;

import com.lei.entity.Seckill;
import com.lei.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws  Exception{
        long id=1000;
        long phone=19852835206L;
        int insertCount=successKilledDao.insertSuccessKilled(id,phone);
        System.out.println(insertCount);
    }
    /*
    10:29:08.263 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@1807f5a7] will not be managed by Spring
    10:29:08.267 [main] DEBUG c.l.d.S.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id,user_phone) values (?,?)
    10:29:08.305 [main] DEBUG c.l.d.S.insertSuccessKilled - ==> Parameters: 1000(Long), 19852835206(Long)
    10:29:08.419 [main] DEBUG c.l.d.S.insertSuccessKilled - <==    Updates: 1
    * */
    /*
    * 第一次执行：1
    * 第二次执行：0
    * 主键是id，phone，重复插入会冲突，（sql中写了ignore防止冲突）所以第二次插入不允许插入
    * */

    @Test
    public void testQueryByIdWithSeckill() throws Exception{
        long id=1000;
        long phone=19852835206L;
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
/*
        * SuccessKilled{seckillId=1000,
        * userPhone=19852835206,
        * state=0,                 ----------------->>>>>成功
        * 如果是-1，则意思是------------------------>>>>>失败
         * createTime=Tue May 12 10:29:08 CST 2020}
*
*
        Seckill{seckillId=1000, name='1000元秒杀iphone6', number=99,
        startTime=Fri May 01 00:00:00 CST 2020,
        endTime=Sun Nov 01 00:00:00 CST 2020,
         createTime=Sat May 02 00:00:00 CST 2020}
* */
}