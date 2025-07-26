package com.CCMS.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    public User() {}
    
    @NonNull
    String name;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    List<Car> cars;

    @Enumerated(EnumType.ORDINAL)
    UserTypes userType;

}
