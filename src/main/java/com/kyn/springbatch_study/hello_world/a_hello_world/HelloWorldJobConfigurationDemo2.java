package com.kyn.springbatch_study.hello_world.a_hello_world;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 *  创建Job包含多个Step
 *  测试类 com.kyn.springbatch_study.hello_world.Test.test2()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo2 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 创建 Job
     * @return
     */
    @Bean
    public Job buildHelloWorldJob2(){
        return jobBuilderFactory.get("helloWorldJob2")
                .incrementer(new RunIdIncrementer())
                .start(helloWorldJob2step1())
                .next(helloWorldJob2step2())
                .build();
    }

    /**
     * 创建 Step1
     * @return
     */
    @Bean
    public Step helloWorldJob2step1() {
        return stepBuilderFactory.get("helloWorldJob2step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("执行....helloWorldJob2step1!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step2
     * @return
     */
    @Bean
    public Step helloWorldJob2step2() {
        return stepBuilderFactory.get("helloWorldJob2step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("执行....helloWorldJob2step2!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };
}
