package com.lei.dao;

import com.lei.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception{
        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.printf(seckill.toString());
    }
    /*Seckill{seckillId=1000, name='1000元秒杀iphone6',
    number=100,
    startTime=Fri May 01 00:00:00 CST 2020,
    endTime=Sun Nov 01 00:00:00 CST 2020,
    createTime=Sat May 02 00:00:00 CST 2020}*/

    @Test
    public void testQueryAll() throws Exception{
        List<Seckill> seckills=seckillDao.queryAll(0,4);
        for(Seckill s: seckills){
            System.out.println(s);
        }
    }
    /*
    Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}
    Seckill{seckillId=1001, name='500元秒杀iPad2', number=200, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}
    Seckill{seckillId=1002, name='300元秒杀小米4', number=300, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}
    Seckill{seckillId=1003, name='200元秒杀红米note', number=400, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}
*/

    @Test
    public void testReduceNumber() throws Exception{
        Date killTime =new Date();
        int update=seckillDao.reduceNumber(1000L,killTime);
        System.out.println(update);
    }
    /*1*/
}