package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;


/**
 * @author Kangyanan
 * @Description: 自定义SkipListener
 * @date 2021/2/25
 */
@Component
public class MySkipListener implements SkipListener {
    private static final Logger logger = LoggerFactory.getLogger(MySkipListener.class);

    @Override
    public void onSkipInRead(Throwable t) {
        if (t instanceof FlatFileParseException) {
            logger.info("进入...onSkipInRead:出错行：{}，出错内容：{}",
                    ((FlatFileParseException) t).getLineNumber(),
                    ((FlatFileParseException) t).getInput());
        }
    }


    @Override
    public void onSkipInWrite(Object o, Throwable throwable) {
        System.out.println("skip write...");
    }

    @Override
    public void onSkipInProcess(Object o, Throwable throwable) {
        System.out.println("skip process...");
    }

}

