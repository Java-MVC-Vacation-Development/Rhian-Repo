package com.CCMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "engine_configuration")
public class EngineConfig extends BaseEntity{

    @NonNull
    String configName;

    Long engineMainSyncTime;

    Boolean demonUse;

    Boolean ecoUse;

    Boolean standardUse;

}
