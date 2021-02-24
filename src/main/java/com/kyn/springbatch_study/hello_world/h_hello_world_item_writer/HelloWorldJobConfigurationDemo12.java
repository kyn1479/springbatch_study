package com.kyn.springbatch_study.hello_world.h_hello_world_item_writer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 *   ItemWriter数据输出：
 *      itemReader是一条一条的读取数据，ItemWriter是一批一批的输出
 *   创建：
 *      MyWriter
 *  测试类 com.kyn.springbatch_study.hello_world.h_hello_world_item_writer.Test.test1()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo12 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** 注入ItemWriter*/
    @Autowired
    @Qualifier("myWriter")
    private ItemWriter<String> myWriter;

    @Bean
    public Job  buildHelloWorldItemWriterJob12(){
        return jobBuilderFactory.get("helloWorldItemWriterJob12")
                .start(itemWriterJob12Step1())
                .build();
    }

    /**
     * 使用chunk实现 Step
     * @return
     */
    @Bean
    public Step itemWriterJob12Step1() {
        return stepBuilderFactory.get("itemWriterJob12Step1")
                .<String, String>chunk(3)  // read process write
                .reader(myReader())
                .writer(myWriter)
                .build();
    }

    @Bean
    public ItemReader<String> myReader() {
        List<String> item=new ArrayList<>();
        for(int i=1;i<50;i++){
            item.add("Java"+i);
        }
        return new ListItemReader<String>(item);
    }
}
