package io.admin.framework.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.admin.framework.data.DBConstants;
import io.admin.framework.data.id.CustomGenerateIdProperties;
import io.admin.framework.data.id.CustomId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
@Slf4j
@EqualsAndHashCode(of = "id")
public abstract class BaseIdEntity implements PersistEntity, Serializable {

    @Id
    @CustomId
    @Column(length = DBConstants.LEN_ID, updatable = false)
    protected String id;



    @JsonIgnore
    @Transient
    public boolean isNew() {
        String theId = getId();
        return null == theId;
    }

    @Override
    public String customGenerateId(CustomGenerateIdProperties properties) {
        return null;
    }
}
