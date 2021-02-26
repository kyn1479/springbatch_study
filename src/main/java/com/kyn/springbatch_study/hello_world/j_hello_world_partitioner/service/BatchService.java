package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service;

/**
 * @author Kangyanan
 * @Description: 批量处理服务接口
 * @date 2021/2/26
 */
public interface BatchService {
    /**
     * @Description 执行批量发送任务
     * @Params
     * @Return
     * @Exceptions
     */
    public void batchSplitAndSend();
}
