package com.CCMS.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "engine_configuration")
public class EngineConfig extends BaseEntity{

    String configName;

}
