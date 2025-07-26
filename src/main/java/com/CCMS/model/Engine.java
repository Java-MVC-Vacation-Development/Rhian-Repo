package com.CCMS.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "engine")
public class Engine extends BaseEntity{

    @NonNull
    String name;

    Boolean electrical;

    byte cilinders;

    @Enumerated(EnumType.ORDINAL)
    Fuel fuel;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
        name="engine_config_id",
        nullable=true,
        foreignKey = @ForeignKey(
        foreignKeyDefinition = "FOREIGN KEY (engine_config_id) REFERENCES engine_configuration(id) ON DELETE CASCADE")
        )
    EngineConfig engineConfig;

}
