package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.partitioner;

import com.alibaba.fastjson.JSON;
import com.kyn.springbatch_study.common.constant.BatchConstant;
import com.kyn.springbatch_study.common.enums.ProcessStatusEnum;
import com.kyn.springbatch_study.common.utils.DateUtil;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.entity.BatchInstInfoEntity;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.repository.DeductTransRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Kangyanan
 * @Description: 数据分片
 * @date 2021/2/26
 */
@Component
public class BatchSplitPartitioner extends PartionerBase implements Partitioner {
    private static final Logger logger = LoggerFactory.getLogger(BatchSplitPartitioner.class);

    @Autowired
    private DeductTransRepository deductTransRepository;

    @Override
    public Map<String, ExecutionContext> partition(int i) {
        return getExecutionMap(i);
    }

    /**
     * 分片并将分片信息放入上下文中
     * @param gridSize
     * @return
     */
    private Map<String, ExecutionContext> getExecutionMap(int gridSize) {
        long startTime = System.currentTimeMillis();
        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
        //TODO 待查询的批次渠道信息
        List<BatchInstInfoEntity> batchInstInfoEntities = Arrays.asList(
                new BatchInstInfoEntity("payCib",3,"62200010002003004"),
                new BatchInstInfoEntity("payCib",4,"63300050005005005"),
                new BatchInstInfoEntity("payCmb",5,"61002000300400059"));

        List<Map<String, Object>> mapList=getMapList(batchInstInfoEntities);
        //分片拆分
        logger.info("分片后的数量为{}", mapList.size());
        //数据放入上下文
        for (Map<String, Object> singleSendMap : mapList) {
            ExecutionContext value = new ExecutionContext();
            value.put(BatchConstant.SINGLE_SEND_MAP, singleSendMap);
            result.put(UUID.randomUUID().toString(), value);
        }
        long endTime = System.currentTimeMillis();

        logger.info("批扣分片时间为：{}ms", endTime - startTime);
        return result;
    }

    private List<Map<String, Object>> getMapList(List<BatchInstInfoEntity> batchInstInfoEntities) {
        //当前时间
        Date nowDateTime = DateUtil.getCurrentDateTime();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (BatchInstInfoEntity batchInstInfoEntity : batchInstInfoEntities) {
            Long minId = 0L;
            String instAcctNo = batchInstInfoEntity.getInstAcctNo() == null ? "" : batchInstInfoEntity.getInstAcctNo();
            while (true) {
                logger.info("查询条件id大于{}, 分片查询数量为{}", minId, batchInstInfoEntity.getSplitSum());
                Long beginId = deductTransRepository.queryMinIdLimitInfo(ProcessStatusEnum.ACCEPT.getCode(),
                        batchInstInfoEntity.getInstCode(),
                        minId, instAcctNo, batchInstInfoEntity.getSplitSum());

                Long endId = deductTransRepository.queryMaxIdLimitInfo(ProcessStatusEnum.ACCEPT.getCode(),
                        batchInstInfoEntity.getInstCode(),
                        minId, instAcctNo, batchInstInfoEntity.getSplitSum());

                if (beginId == null || endId == null) {
                    break;
                }
                mapList.add(genIdMap(beginId, endId, nowDateTime, batchInstInfoEntity));
                minId = endId;
            }
        }
        return mapList;
    }

    private Map<String, Object> genIdMap(Long beginId, Long endId, Date nowDateTime, BatchInstInfoEntity batchInstInfoEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginId", beginId);
        map.put("endId", endId);
        map.put("nowDateTime", nowDateTime);
        map.put("batchInstInfoEntity", batchInstInfoEntity);
        logger.info("分片处理，初始化分片数据 beginId->{} endId -> {} nowDateTime -> {} batchInstInfoEntity ->", beginId, endId, nowDateTime,
                JSON.toJSONString(batchInstInfoEntity));
        return map;
    }
}
