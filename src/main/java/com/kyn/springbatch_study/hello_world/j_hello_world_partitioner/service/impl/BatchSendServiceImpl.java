package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service.impl;

import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.BatchInfo;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.DeductTrans;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service.BatchSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/26
 */
@Service
public class BatchSendServiceImpl implements BatchSendService {
    private static Logger logger = LoggerFactory.getLogger(BatchSendServiceImpl.class);
    @Override
    public void send(BatchInfo batchInfo, List<DeductTrans> deductTranses) {
        logger.info("接收到数据------batchInfo：{},----deductTranses:{}",batchInfo.toString(),deductTranses.toString());
    }
}
