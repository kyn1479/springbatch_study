package com.kyn.springbatch_study.hello_world.c_hello_world_split;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * Split 实现并发执行
 *   实现任务中多个Step或者多个Flow并发执行
 *   创建若干个Step
 *   创建若干个folw
 * 测试类 com.kyn.springbatch_study.hello_world_split.Test.test1()
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
    public Job buildHelloWorldSplitJob5(){
        return jobBuilderFactory.get("helloWorldSplitJob5")
                .start(flowDemo1())
                .split(new SimpleAsyncTaskExecutor())
                .add(flowDemo2())
                .end().build();
    }

    /**
     * 创建Flow 对象 指定包含哪些step
     * @return
     */
    @Bean
    public Flow flowDemo1(){
        return new FlowBuilder<Flow>("flowDemo1")
                .start(helloWorldSplitJob5step1())
                .next(helloWorldSplitJob5step2())
                .build();
    }
    /**
     * 创建Flow 对象 指定包含哪些step
     * @return
     */
    @Bean
    public Flow flowDemo2(){
        return new FlowBuilder<Flow>("flowDemo2")
                .start(helloWorldSplitJob5step3())
                .next(helloWorldSplitJob5step4())
                .build();
    }

    /**
     * 创建 Step1
     * @return
     */
    @Bean
    public Step helloWorldSplitJob5step1() {
        return stepBuilderFactory.get("helloWorldSplitJob5step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldSplitJob5step1!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step2
     * @return
     */
    @Bean
    public Step helloWorldSplitJob5step2() {
        return stepBuilderFactory.get("helloWorldSplitJob5step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldSplitJob5step2!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step3
     * @return
     */
    @Bean
    public Step helloWorldSplitJob5step3() {
        return stepBuilderFactory.get("helloWorldSplitJob5step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldSplitJob5step3!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step4
     * @return
     */
    @Bean
    public Step helloWorldSplitJob5step4() {
        return stepBuilderFactory.get("helloWorldSplitJob5step4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldSplitJob5step4!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

}
