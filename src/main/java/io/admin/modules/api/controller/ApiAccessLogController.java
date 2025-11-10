package io.admin.modules.api.controller;

import io.admin.modules.api.service.ApiAccessLogService;
import io.admin.modules.api.entity.ApiAccessLog;
import io.admin.framework.persistence.BaseController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/apiAccessLog")
public class ApiAccessLogController  extends BaseController<ApiAccessLog>{

    @Resource
    ApiAccessLogService service;




}

