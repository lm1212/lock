package com.lm.controller;

import com.lm.common.RedisTemplateOperator;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    RedisTemplateOperator redisTemplateOperator;

    @Qualifier("redissonSingle")
    RedissonClient redissonClient;

    @GetMapping("getValue/{key}")
    public Object getValue(HttpServletRequest request, @PathVariable("key") String key){
        log.info("请求地址={},请求参数={}",request.getRequestURI(),key);
          return redisTemplateOperator.get(key);
    }

    @PostMapping("setValue")
    public void setValue(HttpServletRequest request,String key){
        log.info("请求地址={},请求参数={}",request.getRequestURI(),key);

        redisTemplateOperator.set(key,"lm");

    }

    @GetMapping("/task")
    public void task(){
        log.info("task start");
        RLock lock = redissonClient.getLock("lm");
        boolean getLock = false;

        try {
            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)){
                //执行业务逻辑
                System.out.println("拿到锁干活");

            }else {
                log.info("Redisson分布式锁没有获得锁:{},ThreadName:{}","lm",Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            log.error("Redisson 获取分布式锁异常,异常信息:{}",e);
        }finally {


            if (!getLock){
                return;
            }
            //如果演示的话需要注释该代码;实际应该放开
            //lock.unlock();
            //log.info("Redisson分布式锁释放锁:{},ThreadName :{}", KeyConst.REDIS_LOCK_KEY, Thread.currentThread().getName());
        }
    }
}
