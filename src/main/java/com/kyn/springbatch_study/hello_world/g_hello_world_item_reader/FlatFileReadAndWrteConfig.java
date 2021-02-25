package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.MaxTemperatureEntiry;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.WeatherEntity;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.processor.ToFileProcessor;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.reader.ToFileReader;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.writer.ToFileWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Kangyanan
 * @Description: 从文件读取并写入文件
 * @date 2021/2/25
 * com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.Test.test4()
 */
@Configuration
@Import({ ToFileReader.class, ToFileProcessor.class, ToFileWriter.class })
public class FlatFileReadAndWrteConfig {
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
    @Qualifier("toFlatFileProcessor")
    private ItemProcessor<WeatherEntity, MaxTemperatureEntiry> processor;
    @Autowired
    @Qualifier("toFlatFileWriter")
    private ItemWriter<MaxTemperatureEntiry> writer;

    @Bean
    public Job buildFlatFileReadAndWrteConfig(){
        return jobBuilderFactory.get("BuildFlatFileReadAndWrteConfig")
                .start(flatfileStep())
                .build();
    }

    @Bean
    public Step flatfileStep() {
        return stepBuilderFactory.get("flatfileStep")
                .<WeatherEntity, MaxTemperatureEntiry>chunk(1000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
