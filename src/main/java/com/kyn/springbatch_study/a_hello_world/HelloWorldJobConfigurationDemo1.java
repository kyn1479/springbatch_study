package com.kyn.springbatch_study.a_hello_world;

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
 *  创建Job包含一个Step
 *  测试类 com.kyn.springbatch_study.hello_world.Test1.test1()
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
    public Job buildHelloWorldJob(){
        return jobBuilderFactory.get("helloWorldJob")
                //用于启动下一个实例JobInstance
                //参考文档：https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html#JobParametersIncrementer
                .incrementer(new RunIdIncrementer())
                .start(helloWorldstep())
                .build();
    }

    /**
     * 创建 Step
     * @return
     */
    @Bean
    public Step helloWorldstep() {
        return stepBuilderFactory.get("helloWorldstep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Spring Batch.... Hello World!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    };
}
