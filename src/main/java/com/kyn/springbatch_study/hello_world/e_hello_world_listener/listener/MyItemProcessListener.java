package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.ItemProcessListener;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Component
public class MyItemProcessListener implements ItemProcessListener {
    private static final Logger logger = LoggerFactory.getLogger(MyItemProcessListener.class);
    @Override
    public void beforeProcess(Object o) {
        logger.info("进入--MyItemProcessListener--beforeProcess");
    }

    @Override
    public void afterProcess(Object o, Object o2) {
        logger.info("进入--MyItemProcessListener--afterProcess");
    }

    @Override
    public void onProcessError(Object o, Exception e) {
        logger.info("进入--MyItemProcessListener--onProcessError");
    }
}
