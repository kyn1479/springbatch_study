package com.kyn.springbatch_study.hello_world.h_hello_world_item_writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/23
 */
@Component("myWriter")
public class MyWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println("进入..MyWriter,数据量："+list.size());
        for (String str:list){
            System.out.println(str);
        }
    }
}
