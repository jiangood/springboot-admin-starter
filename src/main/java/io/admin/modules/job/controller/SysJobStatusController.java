package io.admin.modules.job.controller;

import cn.hutool.core.date.DateUtil;
import io.admin.common.dto.AjaxResult;
import io.admin.common.utils.DbUtils;
import io.admin.framework.config.security.HasPermission;
import jakarta.annotation.Resource;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("admin/jobStatus")
public class SysJobStatusController {


    @Resource
    private Scheduler scheduler;



    @Resource
    private DbUtils db;







}
