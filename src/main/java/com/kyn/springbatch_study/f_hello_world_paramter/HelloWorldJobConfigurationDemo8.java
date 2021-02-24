package com.kyn.springbatch_study.f_hello_world_paramter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: SpringBatch Hello World 入门程序
 * Job参数：
 *   在Job运行中可以以 key:value 形式传递参数
 *   使用listener监听进行传递参数
 *   创建：MyStepListener
 * 测试类 com.kyn.springbatch_study.f_hello_world_paramter.Test.test1()
 * @date 2021/2/24 parameter
 */
@Configuration
public class HelloWorldJobConfigurationDemo8 {
    /** 创建Job工厂对象*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建Step工厂对象*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 创建 Job
     * @return
     */
    @Bean
    public Job buildHelloWorldParamterJob8(){
        return jobBuilderFactory.get("helloWorldParamterJob8")
                .start(helloWorldParamterJob8step1())
                .build();
    }


    /**
     * 使用Tasklet实现Step 并监听传值
     * @return
     */
    @Bean
    public Step helloWorldParamterJob8step1() {
        return stepBuilderFactory.get("helloWorldParamterJob8step1")
                .listener(new MyStepListener())
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("进入...helloWorldParamterJob8step1");
                        //输出接收到的值并且遍历
                        Map<String, Object> map = chunkContext.getStepContext().getStepExecutionContext();
                        map.forEach((k, v) -> System.out.println("上下文中值：key:value = " + k + ":" + v));

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
