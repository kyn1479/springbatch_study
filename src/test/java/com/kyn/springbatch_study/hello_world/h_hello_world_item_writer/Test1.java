package com.kyn.springbatch_study.hello_world.h_hello_world_item_writer;

import com.kyn.springbatch_study.SpringBatchApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBatchApplication.class)
@WebAppConfiguration
public class Test1 {
    private static final Logger logger = LoggerFactory.getLogger(Test1.class);

    @Resource
    private HelloWorldItemWriterJobConfigurationDemo1 helloWorldJobConfigurationDem1;
    @Resource
    private HelloWorldItemWriterJobConfigurationDemo2 helloWorldItemWriterJobConfigurationDemo2;
    @Resource
    private HelloWorldItemWriterJobConfigurationDemo3 helloWorldItemWriterJobConfigurationDemo3;

    /**
     * 任务起动器
     */
    @Autowired
    private JobLauncher jobLauncher;
    /**
     * 测试1
     */
    @Test
    public void test1(){
        Job job = (Job)helloWorldJobConfigurationDem1.buildHelloWorldItemWriterJob12();
        try {
            /* 运行Job */
            JobExecution result = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
            /* 处理结束，控制台打印处理结果 */
            logger.info(result.toString());
            String status = result.getStatus().toString();
            if (status.equals("COMPLETED")) {
                logger.info("JOB执行成功！");
            } else {
                logger.info("JOB执行失败！");
            }
        } catch (Exception e) {
            logger.error("Job执行系统异常", e);
        }
    }

    /**
     * 测试2
     */
    @Test
    public void test2(){
        Job job = (Job) helloWorldItemWriterJobConfigurationDemo2.buildHelloWorldItemWriterJob13();
        try {
            /* 运行Job */
            JobExecution result = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
            /* 处理结束，控制台打印处理结果 */
            logger.info(result.toString());
            String status = result.getStatus().toString();
            if (status.equals("COMPLETED")) {
                logger.info("JOB执行成功！");
            } else {
                logger.info("JOB执行失败！");
            }
        } catch (Exception e) {
            logger.error("Job执行系统异常", e);
        }
    }
    /**
     * 测试3
     */
    @Test
    public void test3(){
        Job job = (Job) helloWorldItemWriterJobConfigurationDemo3.buildHelloWorldItemWriterJob14();
        try {
            /* 运行Job */
            JobExecution result = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
            /* 处理结束，控制台打印处理结果 */
            logger.info(result.toString());
            String status = result.getStatus().toString();
            if (status.equals("COMPLETED")) {
                logger.info("JOB执行成功！");
            } else {
                logger.info("JOB执行失败！");
            }
        } catch (Exception e) {
            logger.error("Job执行系统异常", e);
        }
    }

}