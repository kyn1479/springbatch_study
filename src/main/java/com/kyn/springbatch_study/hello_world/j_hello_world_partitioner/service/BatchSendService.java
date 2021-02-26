
package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service;

import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.BatchInfo;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.DeductTrans;

import java.util.List;

/**
 * @author Kangyanan
 * @Description: 批处理发送
 * @date 2021/2/26
 */
public interface BatchSendService {

    /**
     * @Description 发送数据给渠道
     * @Params batchInfo 批次信息
     * @Exceptions
     */
    public void send(BatchInfo batchInfo, List<DeductTrans> deductTranses);
}