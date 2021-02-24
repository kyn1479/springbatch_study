package com.kyn.springbatch_study.f_hello_world_paramter;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/22
 */
public class MyStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(stepExecution.getStepName()+"   beforeStep....");

        ExecutionContext executionContext =new ExecutionContext();
        executionContext.put("data","测试数据....");
        stepExecution.getExecutionContext().put("demo",executionContext);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
