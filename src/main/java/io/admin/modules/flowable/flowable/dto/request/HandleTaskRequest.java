package io.admin.modules.flowable.flowable.dto.request;

import io.admin.modules.flowable.flowable.dto.TaskHandleResult;
import lombok.Data;

@Data
public class HandleTaskRequest {

    TaskHandleResult result;
    String taskId;
    String comment;
}
