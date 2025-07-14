package com.CCMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    protected Car() {}
    
    @NonNull
    String name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    User user;

    @ManyToOne
    @JoinColumn(name="engine_id", nullable=false)
    Engine engine;

    @ManyToOne
    @JoinColumn(name="engine_config_id", nullable=false)
    EngineConfig engineConfig;

    @JsonIgnore
    public User getUser() {
        return user;
    }
}