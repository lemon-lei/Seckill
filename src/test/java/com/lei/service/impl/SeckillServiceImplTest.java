package com.lei.service.impl;

import com.lei.dto.Exposer;
import com.lei.dto.SeckillExecution;
import com.lei.entity.Seckill;
import com.lei.exception.RepeatKillException;
import com.lei.exception.SeckillCloseException;
import com.lei.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() {
        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
        System.out.println(list);
    }
    /*[Seckill{seckillId=1000, name='1000元秒杀iphone11', number=99, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020},
        Seckill{seckillId=1001,  name='500元秒杀iPad2', number=200, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}, Seckill{seckillId=1002, name='300元秒杀小米4', number=300, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020},
         Seckill{seckillId=1003, name='200元秒杀红米note', number=400, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}]
     */

    @Test
    public void getById() {
        long id=1000;
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}",seckill);
        System.out.println(seckill.toString());
    }
/*Seckill{seckillId=1000, name='1000元秒杀iphone11', number=99, startTime=Fri May 01 00:00:00 CST 2020, endTime=Sun Nov 01 00:00:00 CST 2020, createTime=Sat May 02 00:00:00 CST 2020}
 */
    @Test
    public void exportSeckillUrl() {
        long id=1001;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
        logger.info("exposer={}",exposer);
        long phone=19852835206L;
        String md5=exposer.getMd5();
        try{
            SeckillExecution execution=seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",execution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }
        }else {
            logger.warn("exposer={}",exposer);
        }
        System.out.println(exposer);
    }
    /*Exposer{exposed=true, md5='12280cbc0e2e17e16dfc5dd371713e21', seckillId=1000, now=0, start=0, end=0}
     */

        @Test
    public void executeSeckill() {
        /*com.lei.exception.RepeatKillException: seckill repeat*/
        long id =1000;
        long phone =19852835207L;
        String md5="12280cbc0e2e17e16dfc5dd371713e21";
        try {
            SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
        }catch (RepeatKillException e1){
            logger.error(e1.getMessage());
        }catch (SeckillCloseException e2){
            logger.error(e2.getMessage());
        }
    }
}