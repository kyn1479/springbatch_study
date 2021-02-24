package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/23
 */
@Component("flatFileWriter")
public class FlatFileWriter implements ItemWriter<Customer> {

    @Override
    public void write(List<? extends Customer> list) throws Exception {
        System.out.println("进入...flatFileWriter");
        for (Customer customer:list){
            System.out.println(customer);
        }
    }
}
