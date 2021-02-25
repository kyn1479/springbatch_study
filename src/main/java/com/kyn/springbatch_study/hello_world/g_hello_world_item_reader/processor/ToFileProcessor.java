package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.processor;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.MaxTemperatureEntiry;
import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.WeatherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;


/**
 * @author Kangyanan
 * @Description: 创建Processor
 * @date 2021/2/25
 */
public class ToFileProcessor {
	private static final Logger logger = LoggerFactory.getLogger(ToFileProcessor.class);
	@Bean
	public ItemProcessor<WeatherEntity, MaxTemperatureEntiry> toFlatFileProcessor() {
		return new ItemProcessor<WeatherEntity, MaxTemperatureEntiry>() {
			@Override
			public MaxTemperatureEntiry process(WeatherEntity item) throws Exception {
				if (WeatherEntity.Type.TMAX.equals(item.getType())) {
					MaxTemperatureEntiry maxTemperatureEntiry = new MaxTemperatureEntiry();
					maxTemperatureEntiry.setSiteId(item.getSiteId());
					maxTemperatureEntiry.setDate(item.getMonth());
					maxTemperatureEntiry.setTemperature(item.getValue().toString());
					maxTemperatureEntiry.setType(item.getType().name());
					return maxTemperatureEntiry;
				} else {
					return null;
				}
			}
		};
	}
}