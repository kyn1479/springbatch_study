package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.repository;

import com.kyn.springbatch_study.common.repository.BaseRepository;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.BatchInfo;
import org.springframework.stereotype.Repository;

/**
 * @author Kangyanan
 * @Description: 批次信息表仓库
 * @date 2021/2/26
 */
@Repository
public interface BatchInfoRepository extends BaseRepository<BatchInfo, Long> {

}
