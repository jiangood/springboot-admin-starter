package io.admin.modules.flowable.core.dto.response;

import lombok.Data;

import java.util.Date;


@Data
public class TaskResponse {
    String id;

    String instanceId;
    String instanceName;
    String instanceStartTime;
    String instanceStarter;


    String createTime;
    String taskName;
    String assigneeInfo;
    String durationInfo;

    String formKey;





}
