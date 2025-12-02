package io.admin.modules.flowable.admin.controller;


import cn.hutool.core.util.StrUtil;
import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.security.HasPermission;
import io.admin.modules.common.LoginUtils;
import io.admin.modules.flowable.core.service.FlowableService;
import io.admin.modules.flowable.core.dto.request.SetAssigneeRequest;
import io.admin.modules.flowable.core.dto.response.MonitorTaskResponse;
import io.admin.modules.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程监控
 */
@RequestMapping("admin/flowable/monitor")
@RestController
@AllArgsConstructor
public class MonitorController {

    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;
    private SysUserService sysUserService;

    private FlowableService flowableService;

    @GetMapping("definitionPage")
    public AjaxResult processDefinition(Pageable pageable) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        long count = query.count();
        List<ProcessDefinition> list = query.listPage((int) pageable.getOffset(), pageable.getPageSize());

        List<Map<String, Object>> mapList = list.stream().map(processDefinition -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", processDefinition.getId());
            map.put("category", processDefinition.getCategory());
            map.put("name", processDefinition.getName());
            map.put("key", processDefinition.getKey());
            map.put("description", processDefinition.getDescription());
            map.put("version", processDefinition.getVersion());
            map.put("resourceName", processDefinition.getResourceName());
            map.put("deploymentId", processDefinition.getDeploymentId());
            map.put("diagramResourceName", processDefinition.getDiagramResourceName());
            map.put("hasStartFormKey", processDefinition.hasStartFormKey());
            map.put("hasGraphicalNotation", processDefinition.hasGraphicalNotation());
            map.put("isSuspended", processDefinition.isSuspended());
            map.put("tenantId", processDefinition.getTenantId());
            map.put("derivedFrom", processDefinition.getDerivedFrom());
            map.put("derivedFromRoot", processDefinition.getDerivedFromRoot());
            map.put("derivedVersion", processDefinition.getDerivedVersion());
            return map;
        }).toList();


        return AjaxResult.ok().data(new PageImpl<>(mapList, pageable, count));
    }

    @GetMapping("processInstance")
    public AjaxResult processInstance(Pageable pageable) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        long count = query.count();
        List<ProcessInstance> list = query.listPage((int) pageable.getOffset(), pageable.getPageSize());

        PageImpl<ProcessInstance> page = new PageImpl<>(list, pageable, count);

        return AjaxResult.ok().data(page);
    }

    @GetMapping("processInstance/close")
    public AjaxResult processInstanceClose(String id) {
        String name = LoginUtils.getUser().getName();
        runtimeService.deleteProcessInstance(id, name + "手动关闭");

        return AjaxResult.ok();
    }


    @GetMapping("task")
    public AjaxResult task(String assignee) {
        TaskQuery query = taskService.createTaskQuery();

        if (StrUtil.isNotEmpty(assignee)) {
            query = flowableService.buildUserTodoTaskQuery(assignee);
        }

        List<Task> list = query.list();

        List<MonitorTaskResponse> responseList = list.stream().map(t -> {
            MonitorTaskResponse r = new MonitorTaskResponse();
            r.setId(t.getId());
            r.setName(t.getName());
            r.setTaskDefinitionKey(t.getTaskDefinitionKey());
            r.setProcessDefinitionId(t.getProcessDefinitionId());
            r.setProcessInstanceId(t.getProcessInstanceId());
            r.setAssignee(t.getAssignee());
            r.setAssigneeLabel(sysUserService.getNameById(t.getAssignee()));
            r.setExecutionId(t.getExecutionId());
            r.setStartTime(t.getInProgressStartTime());
            r.setTenantId(t.getTenantId());

            return r;
        }).toList();


        return AjaxResult.ok().data(new PageImpl<>(responseList));
    }

    @HasPermission("flowableTask:setAssignee")
    @RequestMapping("setAssignee")
    public AjaxResult setAssignee(@RequestBody SetAssigneeRequest request) {
        taskService.setAssignee(request.getTaskId(), request.getAssignee());
        return AjaxResult.ok().msg("设置任务处理人成功");
    }


}
