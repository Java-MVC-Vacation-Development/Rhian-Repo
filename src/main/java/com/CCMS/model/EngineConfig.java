package com.CCMS.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(cascade=CascadeType.DETACH, fetch=FetchType.LAZY, mappedBy="engineConfig")
    List<Car> cars;

    Long engineMainSyncTime;

    Boolean demonUse;

    Boolean ecoUse;

    Boolean standardUse;

    @ManyToOne
    @JoinColumn(name = "engine_id", nullable=true)
    Engine engine;

}
