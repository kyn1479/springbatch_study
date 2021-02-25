package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.Boy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * 数据的输入：
 *   ItemReader的使用：
 *   从数据库中读取数据：JdbcPagingItemReader
 *   创建：DbJdbcWriter
 *  测试类 com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.Test.test2()
 * @date 2021/2/24
 */
@Configuration
public class HelloWorldItemReaderJobConfigurationDemo2 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** 注入数据源*/
    @Autowired
    private DataSource dataSource;

    /** 注入ItemWriter*/
    @Autowired
    @Qualifier("dbJdbcWriter")
    private ItemWriter<Boy> dbJdbcWriter;

    @Bean
    public Job buildHelloWorldItemReaderJob10(){
        return jobBuilderFactory.get("helloWorldItemReaderJob10")
                .start(jdbcPagingItemReaderJob10Step1())
                .build();
    }

    /**
     * 使用chunk实现 Step  并监听
     * @return
     */
    @Bean
    public Step jdbcPagingItemReaderJob10Step1() {
        return stepBuilderFactory.get("jdbcPagingItemReaderJob10Step1")
                .<Boy, Boy>chunk(3)  // read process write
                .reader(dbJdbcReader())
                .writer(dbJdbcWriter)
                .build();
    }

    /**
     * 读取数据--从数据库读取
     * @return
     */
    @Bean
    @StepScope
    public JdbcPagingItemReader<Boy> dbJdbcReader() {
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
