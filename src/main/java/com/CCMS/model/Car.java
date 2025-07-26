package com.CCMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
@Table(name = "cars")
public class Car extends BaseEntity{

    public Car() {}
    
    @NonNull
    String name;

    @ManyToOne
    @JoinColumn(
        name="user_id",
        nullable=true,
        foreignKey = @ForeignKey(
        foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE")
        )
    User user;

    @ManyToOne
    @JoinColumn(
        name="engine_id",
        nullable=true,
        foreignKey = @ForeignKey(
        foreignKeyDefinition = "FOREIGN KEY (engine_id) REFERENCES engine(id) ON DELETE CASCADE")
        )
    Engine engine;

    @JsonIgnore
    public User getUser() {
        return user;
    }
}