package com.CCMS.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
    @NonNull
    String name;
}