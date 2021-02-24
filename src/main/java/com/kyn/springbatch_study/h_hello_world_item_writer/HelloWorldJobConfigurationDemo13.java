package com.kyn.springbatch_study.h_hello_world_item_writer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
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

import javax.sql.DataSource;


/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * ItemWriter数据输出：
 *     数据输出到数据库：从customer.txt 读入然后写入数据库表customer
 *  测试类 com.kyn.springbatch_study.h_hello_world_item_writer.Test.test2()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo13 {
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

    /** 注入数据源*/
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job buildHelloWorldItemWriterJob13(){
        return jobBuilderFactory.get("helloWorldItemWriterJob13")
                .start(itemWriterDbJob13Step1())
                .build();
    }

    /**
     * 使用chunk实现 Step
     * @return
     */
    @Bean
    public Step itemWriterDbJob13Step1() {
        return stepBuilderFactory.get("itemWriterDbJob13Step1")
                .<Customer, Customer>chunk(3)  // read process write
                .reader(flatFileReadDb())
                .writer(itemWriterDb())
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Customer> itemWriterDb() {
        JdbcBatchItemWriter<Customer> writer=new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into customer(id,firstName,lastName,birthday) values"+
                "(:id,:firstName,:lastName,:birthday)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
        return writer;
    }

    /**
     * 读取数据--从文件中读取
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemReader<Customer> flatFileReadDb() {
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
