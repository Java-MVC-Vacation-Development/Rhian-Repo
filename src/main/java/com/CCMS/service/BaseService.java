package com.CCMS.service;

import java.util.List;
import java.util.Optional;

import com.CCMS.model.BaseEntity;
import com.CCMS.repository.BaseRepository;

public interface BaseService<T extends BaseEntity> {

    BaseRepository<T> getRepository();

    default T create(T t) {
        return getRepository().save(t);
    }
    
    default Optional<T> update(T t) {
        if (getRepository().existsById(t.getId())) {
            return Optional.of(getRepository().save(t));
        }
        return Optional.empty();
    }
    
    default Optional<T> get(Long id) {
        return getRepository().findById(id);
    }
    
    default List<T> getAll() {
        return getRepository().findAll();
    }
    
    default void delete(Long id) {
        getRepository().deleteById(id);
    }
    
}