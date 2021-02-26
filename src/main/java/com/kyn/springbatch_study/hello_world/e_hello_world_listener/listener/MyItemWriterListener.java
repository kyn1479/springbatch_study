package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.ItemWriteListener;
import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Component
public class MyItemWriterListener implements ItemWriteListener {
    private static final Logger logger = LoggerFactory.getLogger(MyItemWriterListener.class);
    @Override
    public void beforeWrite(List list) {
        logger.info("进入--MyItemWriterListener--beforeWrite");
    }

    @Override
    public void afterWrite(List list) {
        logger.info("进入--MyItemWriterListener--afterWrite");
    }

    @Override
    public void onWriteError(List<Object> list, Exception e) throws Exception {
        logger.info("进入--MyItemWriterListener--onWriteError");
    }
}

