package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.repository;

import com.kyn.springbatch_study.common.repository.BaseRepository;
import com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.model.DeductTrans;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author Kangyanan
 * @Description: 代扣表仓库
 * @date 2021/2/26
 */
public interface DeductTransRepository extends BaseRepository<DeductTrans, Long> {

    @Query(value = "select Min(t.id) from (select id from deduct_trans where process_status = ?1 and inst_code = ?2 and id > ?3 and acct_no = ?4 order by id limit 0, ?5) t", nativeQuery = true)
    public Long queryMinIdLimitInfo(String processStatus, String instCode, Long beginId, String acctNo,int size);

    @Query(value = "select Max(t.id) from (select id from deduct_trans where process_status = ?1 and inst_code = ?2 and id > ?3 and acct_no = ?4 order by id  limit 0, ?5) t", nativeQuery = true)
    public Long queryMaxIdLimitInfo(String processStatus, String instCode, Long beginId, String acctNo, int size);


    @Query(value = "select * from deduct_trans where process_status = ?1 and inst_code = ?2 and acct_no = ?3 and id between ?4 and ?5 order by id limit 0, ?6", nativeQuery = true)
    public List<DeductTrans> queryByIds(String processStatus, String instCode, String acctNo, Long beginId, Long endId, int splitSum);

}
