package io.admin.modules.job.service;

import io.admin.modules.job.entity.SysJob;
import io.admin.modules.job.entity.SysJobExecuteRecord;
import io.admin.modules.job.quartz.QuartzManager;
import io.admin.modules.job.dao.SysJobExecuteRecordDao;
import io.admin.modules.job.JobDescription;
import io.admin.framework.data.service.BaseService;
import io.admin.framework.data.query.JpaQuery;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysJobService extends BaseService<SysJob> {

    @Resource
    QuartzManager quartzService;

    @Resource
    SysJobExecuteRecordDao sysJobExecuteRecordDao;

    @Resource
    Scheduler scheduler;




    public List<SysJob> findAllEnabled() {
        JpaQuery<SysJob> q = new JpaQuery<>();
        q.eq(SysJob.Fields.enabled, true);

        return baseDao.findAll(q);
    }

    @Override
    public SysJob saveOrUpdateByRequest(SysJob input, List<String> updateKeys) throws Exception {
        String jobClass = input.getJobClass();
        SysJob db= super.saveOrUpdateByRequest(input,updateKeys);

        try{
            Class<?> cls = Class.forName(jobClass);
            JobDescription desc = cls.getAnnotation(JobDescription.class);
            if(desc!= null ){
                db.setGroup(desc.group());
            }
        }catch (Exception e){
            throw new IllegalStateException(jobClass + "不存在，请确认");
        }

        quartzService.deleteJob(db);
        if (db.getEnabled()) {
            quartzService.scheduleJob(db);
        }

        return null;
    }



    @Transactional
    public void deleteJob(String id) throws SchedulerException {
        log.info("删除定时任务 {}", id);
        SysJob job = baseDao.findOne(id);
        Assert.notNull(job, "该任务已被删除，请勿重复操作");
        quartzService.deleteJob(job);

        sysJobExecuteRecordDao.deleteByJobId(id);

        baseDao.deleteById(id);
    }


    //清理日志
    public void clean(List<String> ids){
        List<SysJobExecuteRecord> list = sysJobExecuteRecordDao.findAllById(ids);
        for (SysJobExecuteRecord sysJobExecuteRecord : list) {
            sysJobExecuteRecordDao.delete(sysJobExecuteRecord);
        }
    }

    public Page<SysJobExecuteRecord> findAllExecuteRecord(JpaQuery<SysJobExecuteRecord> q, Pageable pageable) {
      return   sysJobExecuteRecordDao.findAll(q, pageable);
    }

    public Page<SysJob> page(String searchText, Pageable pageable) throws SchedulerException {
        JpaQuery<SysJob> q = new JpaQuery<>();
        q.searchText(searchText, SysJob.Fields.name, SysJob.Fields.jobClass);
        Page<SysJob> page = baseDao.findAll(q, pageable);

        List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
        Map<JobKey, JobExecutionContext> currentlyExecutingJobsMap = currentlyExecutingJobs.stream().collect(Collectors.toMap(ctx -> ctx.getJobDetail().getKey(), ctx -> ctx));


        for (SysJob job : page) {
            SysJobExecuteRecord latest = sysJobExecuteRecordDao.findLatest(job.getId());
            if (latest != null) {
                // TODO
                /*job.putExtData("beginTime", latest.getBeginTime());
                job.putExtData("endTime", latest.getEndTime());
                job.putExtData("jobRunTime", latest.getJobRunTimeLabel());
                job.putExtData("result", latest.getResult());*/
            }

            if (job.getEnabled()) {
                JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
                JobExecutionContext ctx = currentlyExecutingJobsMap.get(jobKey);
                if (ctx != null) {
                    // TODO
                  /*  job.putExtData("executing", true);
                    job.putExtData("fireTime", ctx.getFireTime());*/
                }
            }
        }

        return page;
    }
}
