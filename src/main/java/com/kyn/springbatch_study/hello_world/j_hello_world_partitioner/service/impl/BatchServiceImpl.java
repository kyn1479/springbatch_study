package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service.impl;

import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.config.BatchPartitionerConfiguration;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Service
public class BatchServiceImpl implements BatchService {
    private static final Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);

    /** 批量任务配置*/
    @Autowired
    @Qualifier("batchPartitionerConfiguration")
    private BatchPartitionerConfiguration batchConfiguration;

    /** 任务起动器*/
    @Autowired
    private JobLauncher jobLauncher;

    @Override
    public void batchSplitAndSend() {
        Job job = (Job) batchConfiguration.getBatchDataSplitAndSendJob();
        try {
            /* 运行Job */
            JobExecution result = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
            /* 处理结束，控制台打印处理结果 */
            logger.info("任务结果打印：{}",result.toString());
            String status = result.getStatus().toString();
            if ("COMPLETED".equals(status)) {
                logger.info("JOB执行成功！");
            } else {
                logger.info("JOB执行失败！");
                //TODO 告警通知！
            }
        } catch (Exception e) {
            logger.error("JOB执行系统异常", e);
        } finally {

        }


    }
}
