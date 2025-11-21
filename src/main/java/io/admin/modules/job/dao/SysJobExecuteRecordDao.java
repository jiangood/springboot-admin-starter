package io.admin.modules.job.dao;

import io.admin.modules.job.entity.SysJob;
import io.admin.modules.job.entity.SysJobExecuteRecord;
import io.admin.framework.data.repository.BaseDao;
import io.admin.framework.data.query.JpaQuery;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SysJobExecuteRecordDao extends BaseDao<SysJobExecuteRecord> {


    @Transactional
    public void deleteByJobId(String jobId) {
        JpaQuery<SysJobExecuteRecord> q = new JpaQuery<>();
        q.eq(SysJobExecuteRecord.Fields.sysJob, new SysJob(jobId));
        List<SysJobExecuteRecord> list = findAll(q);
        this.deleteAll(list);
    }






    /**
     * 查询执行记录，倒叙
     * @param job
     * @return
     */
    public List<SysJobExecuteRecord> findByJobDesc(SysJob job) {
        JpaQuery<SysJobExecuteRecord> q = new JpaQuery<>();
        q.eq(SysJobExecuteRecord.Fields.sysJob, job);
        return this.findAll(q, Sort.by(Sort.Direction.DESC,"createTime"));
    }

    public SysJobExecuteRecord findLatestByJob(SysJob job) {
        JpaQuery<SysJobExecuteRecord> q = new JpaQuery<>();
        q.eq(SysJobExecuteRecord.Fields.sysJob, job);
        return this.findTop1(q, Sort.by(Sort.Direction.DESC,"createTime"));
    }

    public long countByJob(SysJob job) {
        JpaQuery<SysJobExecuteRecord> q = new JpaQuery<>();
        q.eq(SysJobExecuteRecord.Fields.sysJob, job);
        return this.count(q);
    }
    public SysJobExecuteRecord findLatest(String jobId) {
        JpaQuery<SysJobExecuteRecord> q = new JpaQuery<>();
        q.eq(SysJobExecuteRecord.Fields.sysJob + ".id", jobId);
        return this.findTop1(q, Sort.by(Sort.Direction.DESC, "createTime"));
    }



}
