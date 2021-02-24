package com.kyn.springbatch_study.hello_world.d_hello_world_decider;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * 决策器的使用
 *   创建自定义：MyDecider 决策器
 *   实现 JobExecutionDecider接口
 * 测试类 com.kyn.springbatch_study.hello_world_decider.Test.test1()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo1 {
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
    public Job buildHelloWorldDeciderJob6(){
        return jobBuilderFactory.get("helloWorldDeciderJob6")
                .start(helloWorldDeciderJob6step1())
                .next(myDecider())
                .from(myDecider()).on("even").to(helloWorldDeciderJob6step2())
                .from(myDecider()).on("odd").to(helloWorldDeciderJob6step3())
                .from(helloWorldDeciderJob6step3()).on("*").to(myDecider())
                .end().build();
    }


    /**
     * 创建 Step1
     * @return
     */
    @Bean
    public Step helloWorldDeciderJob6step1() {
        return stepBuilderFactory.get("helloWorldDeciderJob6step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldDeciderJob6step1!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step2
     * @return
     */
    @Bean
    public Step helloWorldDeciderJob6step2() {
        return stepBuilderFactory.get("helloWorldDeciderJob6step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldDeciderJob6step2!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step3
     * @return
     */
    @Bean
    public Step helloWorldDeciderJob6step3() {
        return stepBuilderFactory.get("helloWorldDeciderJob6step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldDeciderJob6step3!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建决策器
     * @return
     */
    @Bean
    public JobExecutionDecider myDecider(){
        return new MyDecider();
    }


}
