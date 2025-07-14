package com.CCMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @NonNull
    Boolean electrical;

    byte cilinders;

    @Enumerated(EnumType.ORDINAL)
    Fuel fuel;

}
