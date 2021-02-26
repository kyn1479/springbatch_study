package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.tasklet;

import com.kyn.springbatch_study.common.constant.BatchConstant;
import com.kyn.springbatch_study.common.enums.ProcessStatusEnum;
import com.kyn.springbatch_study.common.enums.TransactionEnum;
import com.kyn.springbatch_study.common.utils.DateUtil;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.entity.BatchInstInfoEntity;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.BatchInfo;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.DeductTrans;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.repository.BatchInfoRepository;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.repository.DeductTransRepository;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.service.BatchSendService;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.utils.TaskletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kangyanan
 * @Description: 根据分片进行业务处理
 * @date 2021/2/26
 */
@Component
public class BatchSplitAndSendTask implements Tasklet {
    private static Logger logger = LoggerFactory.getLogger(BatchSplitAndSendTask.class);
    @Autowired
    private DeductTransRepository deductTransRepository;
    @Autowired
    private BatchInfoRepository batchInfoRepository;
    @Autowired
    private BatchSendService batchSendService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Object singleObj = TaskletUtil.getValueFromStepExecutionContext(chunkContext, BatchConstant.SINGLE_SEND_MAP);
        Map<String, Object> singleSendMap = (singleObj == null ? null : (Map<String, Object>) singleObj);
        if (singleSendMap == null || singleSendMap.size() == 0) {
            logger.info("无数据需要处理");
            return RepeatStatus.FINISHED;
        }
        Long beginId = (Long) singleSendMap.get("beginId");
        Long endId = (Long) singleSendMap.get("endId");

        BatchInstInfoEntity batchInstInfoEntity = (BatchInstInfoEntity) singleSendMap.get("batchInstInfoEntity");
        String instAcctNo = batchInstInfoEntity.getInstAcctNo()==null?"":batchInstInfoEntity.getInstAcctNo();
        List<DeductTrans> list=deductTransRepository.queryByIds(ProcessStatusEnum.ACCEPT.getCode(),batchInstInfoEntity.getInstCode(),instAcctNo,beginId,endId,batchInstInfoEntity.getSplitSum());
        //初始化批次信息
        BatchInfo batchInfo=genBatchInfo(batchInstInfoEntity);
        //更新明细数据
        List<DeductTrans> processModes= dealBatchDetail(batchInfo,list);
        //保存批次信息
        batchInfoRepository.save(batchInfo);
        //发送数据
        batchSendService.send(batchInfo, processModes);
        return RepeatStatus.FINISHED;
    }

    /**
     * 批付表数据准备
     * @param batchInstInfoEntity
     * @return
     */
    private BatchInfo genBatchInfo(BatchInstInfoEntity batchInstInfoEntity) {
        BatchInfo batchInfo=new BatchInfo();
        batchInfo.setBatchNo(UUID.randomUUID().toString());
        batchInfo.setAcctNo(batchInstInfoEntity.getInstAcctNo());
        batchInfo.setInstCode(batchInstInfoEntity.getInstCode());
        batchInfo.setTotalSucNum(0L);//成功数量
        batchInfo.setTotalSucAmount(new BigDecimal(0));//成功金额
        batchInfo.setCreateTime(DateUtil.getCurrentDateTime());//创建时间
        batchInfo.setUpdateTime(DateUtil.getCurrentDateTime());//更新时间
        batchInfo.setTransDateTime(DateUtil.getCurrentDateTime());//交易时间
        batchInfo.setBatchStatus(ProcessStatusEnum.INIT.getCode());//批次状态
        batchInfo.setTransCode(TransactionEnum.BATCH_DEDUCT.getCode());
        return batchInfo;
    }

    private List<DeductTrans> dealBatchDetail(BatchInfo batchInfo, List<DeductTrans> deductTransList) {
        BigDecimal totalAmount = new BigDecimal(0);
        Long totalNum = 0L;
        for (int i = 0; i < deductTransList.size(); i++) {
            DeductTrans deductTrans = deductTransList.get(i);
            deductTrans.setProcessStatus(ProcessStatusEnum.SPLIT_BATCH_SUCCESS.getCode());
            deductTrans.setBatchNo(batchInfo.getBatchNo());
            deductTrans.setInstReqNo(batchInfo.getInstReqNo() + (i + 1));
            totalAmount = totalAmount.add(deductTrans.getTransAmount());//计算金额
            totalNum += 1;
        }
        batchInfo.setTotalNum(totalNum);
        batchInfo.setTotalAmount(totalAmount);

        logger.info("该批次({})明细数据更新成功, 一共有数据条数{}, 总金额{}", batchInfo.getBatchNo(), totalNum, totalAmount);
        return deductTransRepository.saveAll(deductTransList);
    }
}
