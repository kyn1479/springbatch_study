package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.WeatherEntity;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.listener.MySkipListener;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.reader.ToFileReader;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.writer.ToDbWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Kangyanan
 * @Description: 从文件读取并写入数据表
 *    定义：MySkipListener
 * @date 2021/2/25
 * com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.Test.test5()
 */
@Configuration
@Import({ ToFileReader.class, ToDbWriter.class })
public class FlatFileReadAndDbWrteConfig {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("toFlatFileReader")
    private ItemReader<WeatherEntity> reader;

    @Autowired
    @Qualifier("jdbcBatchWriter")
    private ItemWriter<WeatherEntity> writer;

    @Autowired
    private MySkipListener mySkipListener;

    @Bean
    public Job buildFlatFileReadAndDbWrteConfig(){
        return jobBuilderFactory.get("BuildFlatFileReadAndDbWrteConfig")
                .start(dbWriterStep())
                .build();
    }

    @Bean
    public Step dbWriterStep() {
        return stepBuilderFactory.get("dbWriterStep")
                .<WeatherEntity, WeatherEntity>chunk(1000)
                .reader(reader)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .listener(mySkipListener)
                .build();
    }

}
