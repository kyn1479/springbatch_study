package com.kyn.springbatch_study.hello_world.b_hello_world_flow;

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

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * Flow的创建和使用：
 *    flow是多个step的集合
 *    可以被多个job复用
 *    使用FlowBuilder来创建
 * 测试类 com.kyn.springbatch_study.hello_world_flow.Test.test1()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo4 {
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
    public Job buildHelloWorldFlowJob4(){
        return jobBuilderFactory.get("helloWorldFlowJob4")
                .start(flowDemo())
                .next(helloWorldFlowJob4step3())
                .end().build();
    }

    /**
     * 创建Flow 对象 指定包含哪些step
     * @return
     */
    @Bean
    public Flow flowDemo(){
        return new FlowBuilder<Flow>("flowDemo")
                .start(helloWorldFlowJob4step1())
                .next(helloWorldFlowJob4step2())
                .build();
    }

    /**
     * 创建 Step1
     * @return
     */
    @Bean
    public Step helloWorldFlowJob4step1() {
        return stepBuilderFactory.get("helloWorldFlowJob4step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("执行....helloWorldFlowJob4step1!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step2
     * @return
     */
    @Bean
    public Step helloWorldFlowJob4step2() {
        return stepBuilderFactory.get("helloWorldFlowJob4step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("执行....helloWorldFlowJob4step2!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

    /**
     * 创建 Step3
     * @return
     */
    @Bean
    public Step helloWorldFlowJob4step3() {
        return stepBuilderFactory.get("helloWorldFlowJob4step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("执行....helloWorldFlowJob4step3!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };

}
