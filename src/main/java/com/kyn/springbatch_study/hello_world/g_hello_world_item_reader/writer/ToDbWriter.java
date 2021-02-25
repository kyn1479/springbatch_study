package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.writer;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.WeatherEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 创建写数据表的Writer
 * @date 2021/2/25
 */
public class ToDbWriter {

	@Bean
	public ItemWriter<WeatherEntity> jdbcBatchWriter(JdbcTemplate template) {

		return new ItemWriter<WeatherEntity>() {
			final private static String INSERt_SQL = "INSERT INTO tmp_test_weather(siteid, month, type, value, ext) VALUES(?,?,?,?,?)";

			@Override
			public void write(List<? extends WeatherEntity> items) throws Exception {
				List<Object[]> batchArgs = new ArrayList<>();
				for (WeatherEntity entity : items) {
					Object[] objects = new Object[5];
					objects[0] = entity.getSiteId();
					objects[1] = entity.getMonth();
					objects[2] = entity.getType().name();
					objects[3] = entity.getValue();
					objects[4] = entity.getExt();
					batchArgs.add(objects);
				}
				template.batchUpdate(INSERt_SQL, batchArgs);
			}
		};
	}
}
