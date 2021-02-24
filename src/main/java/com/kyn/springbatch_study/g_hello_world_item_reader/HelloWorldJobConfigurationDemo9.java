package com.kyn.springbatch_study.g_hello_world_item_reader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * 数据的输入：
 *   ItemReader的使用：
 *   创建：MyRead
 *  测试类 com.kyn.springbatch_study.g_hello_world_item_reader.Test.test1()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo9 {
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
    public Job buildHelloWorldItemReaderJob9(){
        return jobBuilderFactory.get("helloWorldItemReaderJob9")
                .start(helloWorldItemReadJob9step1())
                .build();
    }



    /**
     * 使用chunk实现 Step
     * @return
     */
    @Bean
    public Step helloWorldItemReadJob9step1() {
        return stepBuilderFactory.get("helloWorldItemReadJob9step1")
                .<String, String>chunk(3)  // read process write
                .reader(helloWorldItemRead9_1())
                .writer(helloWorldItemWrite9_1())
                .build();
    }

    /**
     * 读取数据
     * @return
     */
    @Bean
    public MyRead helloWorldItemRead9_1() {
        List<String> data= Arrays.asList("dog","cat","duck","pig");
        return new MyRead(data);
    }

    /**
     * 写数据
     * @return
     */
    @Bean
    public ItemWriter<String> helloWorldItemWrite9_1() {
        return new ItemWriter<String>(){
            @Override
            public void write(List<? extends String> items) throws Exception {
                System.out.println("进入 helloWorldItemWrite9_1....");
                for (String item:items){
                    System.out.println(item);
                }
            }
        };
    }
}
