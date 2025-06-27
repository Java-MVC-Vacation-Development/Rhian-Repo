package com.CCMS.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @NonNull
    String name;
    
    /*
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "user")
    List<Car> car;
    */

}
