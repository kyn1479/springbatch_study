package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Component
public class MyStepExecutionListener implements StepExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(MyStepExecutionListener.class);
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("进入--MyStepExecutionListener--beforeStep");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("进入--MyStepExecutionListener--afterStep");
        return null;
    }
}
