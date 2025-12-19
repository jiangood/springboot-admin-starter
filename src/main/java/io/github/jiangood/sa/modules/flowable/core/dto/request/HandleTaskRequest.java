package io.github.jiangood.sa.modules.flowable.core.dto.request;

import io.github.jiangood.sa.modules.flowable.core.dto.TaskHandleType;
import lombok.Data;

@Data
public class HandleTaskRequest {

    TaskHandleType result;
    String taskId;
    String comment;
}
