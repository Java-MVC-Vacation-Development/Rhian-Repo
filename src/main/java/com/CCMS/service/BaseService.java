package com.CCMS.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import com.CCMS.model.BaseEntity;
import com.CCMS.repository.BaseRepository;

@NoRepositoryBean
public class BaseService<Entity extends BaseEntity, Repository extends BaseRepository<Entity>> {

    @Autowired
    public Repository repository;

    public BaseService(Repository repository){
        this.repository = repository;
    }

    public Repository getRepository(){
        return this.repository;
    };

    public Entity create(Entity t) {
        return getRepository().save(t);
    }
    
    public Optional<Entity> update(Entity t) {
        if (getRepository().existsById(t.getId())) {
            return Optional.of(getRepository().save(t));
        }
        return Optional.empty();
    }
    
    public Optional<Entity> get(Long id) {
        return getRepository().findById(id);
    }
    
    public Iterator<Entity> getAll() {
        return getRepository().findAll().iterator();
    }
    
    public Optional<Entity> delete(Long id) {

        getRepository().deleteById(id);

        return getRepository().findById(id);
    }
    
}