package com.kyn.springbatch_study.hello_world.h_hello_world_item_writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 *   ItemWriter数据输出：
 *      数据输出到普通文件：从数据库表中读取然后写入文件
 *  测试类 com.kyn.springbatch_study.hello_world.h_hello_world_item_writer.Test.test3()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldJobConfigurationDemo3 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    /** 注入数据源*/
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job buildHelloWorldItemWriterJob14(){
        return jobBuilderFactory.get("helloWorldItemWriterJob14")
                .start(itemWriterFileJob14Step1())
                .build();
    }

    /**
     * 使用chunk实现 Step
     * @return
     */
    @Bean
    public Step itemWriterFileJob14Step1() {
        return stepBuilderFactory.get("itemWriterFileJob14Step1")
                .<Boy, Boy>chunk(3)  // read process write
                .reader(dbJdbcWriterRead())
                .writer(itemWriterFile())
                .build();
    }

    /**
     * 写入文件
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemWriter<Boy> itemWriterFile() {
        //把对象转换为字符串输出到文件
        FlatFileItemWriter<Boy> writer=new FlatFileItemWriter<>();
        String path="d:\\boy.txt";
        writer.setResource(new FileSystemResource(path));
        //Json格式
        writer.setLineAggregator(new LineAggregator<Boy>() {
            ObjectMapper mapper=new ObjectMapper();
            @Override
            public String aggregate(Boy boy) {
                String str=null;
                try {
                    str=mapper.writeValueAsString(boy);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return str;
            }
        });
        //指定字符串格式
//        writer.setLineAggregator(new LineAggregator<Boy>() {
//            ObjectMapper mapper=new ObjectMapper();
//            @Override
//            public String aggregate(Boy boy) {
//                StringBuilder sb=new StringBuilder();
//                try {
//                    sb.append(boy.getId()).append("|").append(boy.getName()).append("|").append(boy.getSex());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return sb.toString();
//            }
//        });

        return writer;
    }

    /**
     * 读取数据--从数据库读取
     * @return
     */
    @Bean
    @StepScope
    public JdbcPagingItemReader<Boy> dbJdbcWriterRead() {
        JdbcPagingItemReader<Boy> reader=new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(3);
        //把读取的记录转换成Boy对象
        reader.setRowMapper(new RowMapper<Boy>() {
            @Override
            public Boy mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Boy boy=new Boy();
                boy.setId(resultSet.getLong(1));
                boy.setName(resultSet.getString(2));
                boy.setSex(resultSet.getByte(3));
                return boy;
            }
        });
        //指定Sql语句
        MySqlPagingQueryProvider provider=new MySqlPagingQueryProvider();
        provider.setSelectClause("id,name,sex");
        provider.setFromClause("from boy");
        //根据哪个字段排序
        Map<String, Order> sort=new HashMap<>(1);
        sort.put("id",Order.ASCENDING);
        provider.setSortKeys(sort);
        reader.setQueryProvider(provider);
        return reader;
    }
}
