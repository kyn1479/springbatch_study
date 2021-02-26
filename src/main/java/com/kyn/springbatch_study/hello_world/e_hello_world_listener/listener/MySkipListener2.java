package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Component
public class MySkipListener2 implements SkipListener {
    private static final Logger logger = LoggerFactory.getLogger(MySkipListener2.class);
    @Override
    public void onSkipInRead(Throwable throwable) {
        logger.info("进入--MySkipListener--onSkipInRead");
    }

    @Override
    public void onSkipInWrite(Object o, Throwable throwable) {
        logger.info("进入--MySkipListener--onSkipInWrite");
    }

    @Override
    public void onSkipInProcess(Object o, Throwable throwable) {
        logger.info("进入--MySkipListener--onSkipInProcess");
    }
}
