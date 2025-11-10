package io.admin.framework.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.admin.framework.data.id.CustomGenerateIdProperties;

public interface PersistEntity {

    void setId(String id);

    String getId();


    /**
     * 自定义id的值
     *
     * @return
     */
    @JsonIgnore
    default String get_tempId() {
        return null;
    }

    default void set_tempId(String id) {

    }


    // 动态生成的自定义Id
    @JsonIgnore
    String customGenerateId(CustomGenerateIdProperties properties);
}
