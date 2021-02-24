package com.kyn.springbatch_study.hello_world.e_hello_world_listener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * 监听器的使用：
 *   用来监听批处理作业的执行情况
 *   可以通过实现接口或者使用注解
 *   继承接口创建Job级别监听 MyJobListener
 *   使用注解创建Chunk级别监听 MyChunkListener
 * 测试类 com.kyn.springbatch_study.hello_world_listener.Test.test1()
 * @date 2021/2/24 parameter
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
    public Job buildHelloWorldListenerJob7(){
        return jobBuilderFactory.get("helloWorldListenerJob7")
                .start(helloWorldListenerJob7step1())
                .listener(new MyJobListener())
                .next(helloWorldListenerJob7step2())
                .build();
    }


    /**
     * 使用chunk实现 Step  并监听
     * @return
     */
    @Bean
    public Step helloWorldListenerJob7step1() {
        return stepBuilderFactory.get("helloWorldListenerJob7step1")
                .<String, String>chunk(3)  // read process write
                .faultTolerant() // 容错
                .listener(new MyChunkListener())
                .reader(read())
                .writer(write())
                .build();
    }



    /**
     * 使用Tasklet实现 Step
     * @return
     */
    @Bean
    public Step helloWorldListenerJob7step2() {
        return stepBuilderFactory.get("helloWorldListenerJob7step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(Thread.currentThread().getName()+"执行....helloWorldListenerJob7step2!");
                        return RepeatStatus.FINISHED;//决定是否进行后面的Step
                    }
                }).build();
    }
    /**
     * 读取数据
     * @return
     */
    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("Java","Python","C++","Go"));
    }

    /**
     * 写数据
     * @return
     */
    @Bean
    public ItemWriter<String> write() {
        return new ItemWriter<String>(){
            @Override
            public void write(List<? extends String> items) throws Exception {
                for (String item:items){
                    System.out.println(item);
                }
            }
        };
    }




}
