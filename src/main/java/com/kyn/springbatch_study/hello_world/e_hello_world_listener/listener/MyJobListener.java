package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 使用接口继承实现 Job级别监听器
 * @date 2021/2/22
 */
@Component
public class MyJobListener implements JobExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(MyJobListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("进入--MyJobListener--beforeJob");
//        System.out.println(jobExecution.getJobInstance().getJobName()+"  MyJobListener before...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("进入--MyJobListener--afterJob");
//        System.out.println(jobExecution.getJobInstance().getJobName()+"  after...");
    }
}
