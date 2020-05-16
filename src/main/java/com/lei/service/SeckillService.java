package com.lei.service;

import com.lei.dto.Exposer;
import com.lei.dto.SeckillExecution;
import com.lei.entity.Seckill;
import com.lei.exception.RepeatKillException;
import com.lei.exception.SeckillCloseException;
import com.lei.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws SeckillException, RepeatKillException, SeckillCloseException;



}
