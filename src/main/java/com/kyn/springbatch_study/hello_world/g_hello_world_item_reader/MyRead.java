package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 数据读取
 * @date 2021/2/22
 */
public class MyRead implements ItemReader<String> {

    private Iterator<String> iterator;

    public MyRead(List<String> list) {
        this.iterator = list.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(iterator.hasNext())
            return this.iterator.next();
        else
            return null;
    }
}
