package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.ItemReadListener;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Component
public class MyItemReadListener implements ItemReadListener {
    private static final Logger logger = LoggerFactory.getLogger(MyItemReadListener.class);
    @Override
    public void beforeRead() {
        logger.info("进入--MyItemReadListener--beforeRead");
    }

    @Override
    public void afterRead(Object o) {
        logger.info("进入--MyItemReadListener--afterRead");
    }

    @Override
    public void onReadError(Exception e) {
        logger.info("进入--MyItemReadListener--onReadError");
    }
}

