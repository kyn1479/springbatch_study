package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * 数据的输入：
 *    ItemReader的使用：
 *    从普通文件中读取数据：FlatFileItemReader
 *    创建：FlatFileWriter
 *  测试类 com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.Test.test3()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldItemReaderJobConfigurationDemo3 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** 注入ItemWriter*/
    @Autowired
    @Qualifier("flatFileWriter")
    private ItemWriter<Customer> flatFileWriter;

    @Bean
    public Job buildHelloWorldItemReaderJob11(){
        return jobBuilderFactory.get("helloWorldItemReaderJob11")
                .start(flatFileItemReaderJob11Step1())
                .build();
    }

    /**
     * 使用chunk实现 Step  并监听
     * @return
     */
    @Bean
    public Step flatFileItemReaderJob11Step1() {
        return stepBuilderFactory.get("flatFileItemReaderJob11Step1")
                .<Customer, Customer>chunk(3)  // read process write
                .reader(flatFileRead())
                .writer(flatFileWriter)
                .build();
    }

    /**
     * 读取数据--从文件中读取
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemReader<Customer> flatFileRead() {
        FlatFileItemReader<Customer> reader=new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("customer.txt"));
        reader.setLinesToSkip(1);
        //数据解析
        DelimitedLineTokenizer tokenizer =new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id","firstName","lastName","birthday"});
        //把解析出的数据映射为对象
        DefaultLineMapper<Customer> mapper=new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
            @Override
            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
                Customer customer=new Customer();
                customer.setId(fieldSet.readLong("id"));
                customer.setFirstName(fieldSet.readString("firstName"));
                customer.setLastName(fieldSet.readString("lastName"));
                customer.setBirthday(fieldSet.readString("birthday"));
                return customer;
            }
        });
        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
    }
}
