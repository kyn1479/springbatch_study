package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.writer;

import com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity.Boy;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/23
 */
@Component("dbJdbcWriter")
public class DbJdbcWriter implements ItemWriter<Boy> {
    @Override
    public void write(List<? extends Boy> list) throws Exception {
        System.out.println("进入..DbJdbcWriter");
        for (Boy boy:list) {
            System.out.println(boy);
        }
    }
}
