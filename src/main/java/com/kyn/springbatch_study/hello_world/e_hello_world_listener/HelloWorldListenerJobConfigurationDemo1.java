package com.kyn.springbatch_study.hello_world.e_hello_world_listener;

import com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
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
 * 结果打印：
 *     进入--MyJobListener--beforeJob
 *     进入--MyStepExecutionListener--beforeStep
 *     进入--MyChunkListener--beforeChunk
 *      Java language
 *      Python language
 *      C++ language
 *     进入--MyChunkListener--afterChunk
 *     进入--MyChunkListener--beforeChunk
 *      Go language
 *     进入--MyChunkListener--afterChunk
 *     进入--MyStepExecutionListener--afterStep
 *     进入--MyJobListener--afterJob
 */
@Configuration
public class HelloWorldListenerJobConfigurationDemo1 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MyJobListener myJobExecutionListener;

    @Autowired
    private MyStepExecutionListener myStepExecutionListener;

    @Autowired
    private MyChunkListener myChunkListener;

    @Autowired
    private MyItemReadListener myItemReadListener;

    @Autowired
    private MyItemProcessListener myItemProcessListener;

    @Autowired
    private MyItemWriterListener myItemWriterListener;

    @Autowired
    private MySkipListener2 mySkipListener;

    /**
     * 创建 Job
     * @return
     */
    @Bean
    public Job buildHelloWorldListenerJob7(){
        return jobBuilderFactory.get("helloWorldListenerJob7")
                .start(helloWorldListenerJob7step1())
                .listener(myJobExecutionListener)
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
                .listener(myStepExecutionListener)
                .listener(myChunkListener)
                .listener(myItemReadListener)
                .reader(read())
                .listener(myItemProcessListener)
                .processor(processor())
                .listener(myItemWriterListener)
                .writer(write())
                .listener(mySkipListener)
                .build();

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
     * 处理数据
     * @return
     */
    @Bean
    public ItemProcessor<String,String> processor() {
        return item -> item + " language";
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
