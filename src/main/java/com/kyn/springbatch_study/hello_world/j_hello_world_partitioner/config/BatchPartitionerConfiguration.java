package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.config;

import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.partitioner.BatchSplitPartitioner;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.tasklet.BatchSplitAndSendTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.util.Collection;

/**
 * @author Kangyanan
 * @Description: 分片处理，以批扣为例
 *    待处理数据存储在deduct_trans表
 *    将数据进行分片分片后信息存储在batch_info表
 *    表 batch_info 字段 batch_no 与表 deduct_trans 字段 batch_no 一对多关系！
 * @date 2021/2/26
 */
@Component
@Configuration
@EnableBatchProcessing
public class BatchPartitionerConfiguration {

    /** spring batch job任务构建工厂*/
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** spring batch step构建工栈*/
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** 数据分片*/
    @Autowired
    private BatchSplitPartitioner batchSplitPartitioner;

    /** 真正业务处理*/
    @Autowired
    private BatchSplitAndSendTask batchSplitAndSendTask;

    /**
     * 创建Job
     * @return
     */
    public Job getBatchDataSplitAndSendJob() {
        return jobBuilderFactory.get("getBatchDataSplitAndSendJob")
                .incrementer(new RunIdIncrementer())
                .start(batchCutSplitAndSendFlow())
                .end()
                .build();
    }

    /**
     * 创建Flow
     * @return
     */
    @Bean
    public SimpleFlow batchCutSplitAndSendFlow() {
        return new FlowBuilder<SimpleFlow>("Batch Cut Send Flow")
                .start(batchDataSplitAndSend())
                .end();
    }

    /**
     * 分片和发送Step
     * @return
     */
    @Bean
    public Step batchDataSplitAndSend() {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setStep(batchDataSplitAndsendStep());
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);//todo 配置
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.initialize();
        handler.setTaskExecutor(threadPoolTaskExecutor);
        handler.setGridSize(0);

        return stepBuilderFactory.get("batchDataSplitAndSend")
                .partitioner("batchDataSplitAndsendStep", batchSplitPartitioner).partitionHandler(handler)
                .build();
    }

    /**
     * 发送Step
     * @return
     */
    @Bean
    public Step batchDataSplitAndsendStep() {
        return stepBuilderFactory.get("batchCutSplitAndsendStep")
                .tasklet(batchSplitAndSendTask)
                .transactionAttribute(transactionAttribute)
                .build();
    }


    TransactionAttribute transactionAttribute = new TransactionAttribute() {
        @Override
        public int getPropagationBehavior() {
            return TransactionAttribute.PROPAGATION_NOT_SUPPORTED;
        }

        @Override
        public int getIsolationLevel() {
            return TransactionAttribute.ISOLATION_DEFAULT;
        }

        @Override
        public int getTimeout() {
            return TransactionAttribute.TIMEOUT_DEFAULT;
        }

        @Override
        public boolean isReadOnly() {
            return false;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getQualifier() {
            return null;
        }

        @Override
        public Collection<String> getLabels() {
            return null;
        }

        @Override
        public boolean rollbackOn(Throwable ex) {
            return false;
        }
    };

}
