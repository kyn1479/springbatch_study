package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.reader;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.WeatherEntity;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

/**
 * @author Kangyanan
 * @Description: 从文件读取数据Reader
 * @date 2021/2/25
 */
public class ToFileReader {

    public final static String[] Tokenizer = new String[] { "siteId", "month", "type", "value", "ext" };
    private boolean userWrapper = false;

    /**
     * 定义FieldSetMapper用于FieldSet->WeatherEntity映射
     * @return
     */
    @Bean
    public FieldSetMapper<WeatherEntity> fieldSetMapper() {
        return new FieldSetMapper<WeatherEntity>() {
            @Override
            public WeatherEntity mapFieldSet(FieldSet fieldSet) throws BindException {
                if (null == fieldSet) {
                    return null;
                } else {
                    WeatherEntity observe = new WeatherEntity();
                    observe.setSiteId(fieldSet.readRawString("siteId"));
                    observe.setMonth(fieldSet.readRawString("month"));
                    observe.setType(WeatherEntity.Type.valueOf(fieldSet.readRawString("type")));
                    observe.setValue(Integer.valueOf(fieldSet.readRawString("value")));
                    return observe;
                }
            }
        };
    }

    /**
     *  配置 Reader
     * @param fieldSetMapper
     * @return
     */
    @Bean
    public ItemReader<WeatherEntity> toFlatFileReader(@Qualifier("fieldSetMapper") FieldSetMapper<WeatherEntity> fieldSetMapper) {
        FlatFileItemReader<WeatherEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/data.csv"));
        DefaultLineMapper<WeatherEntity> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(Tokenizer);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);
        reader.setLinesToSkip(1); // 跳过的初始行，用于过滤字段行
        reader.open(new ExecutionContext());
        return reader;
    }
}
