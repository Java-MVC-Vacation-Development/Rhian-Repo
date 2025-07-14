package com.CCMS.controller;

import java.util.Iterator;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CCMS.model.BaseEntity;
import com.CCMS.repository.BaseRepository;
import com.CCMS.service.BaseService;

@NoRepositoryBean
@RequestMapping(path = "/api")
public class BaseController<Entity extends BaseEntity, Repository extends BaseRepository<Entity>, Service extends BaseService<Entity, Repository>> {

    @Autowired
    private final Service service;
    private final Repository repository;

    public BaseController(Repository repository, Service service){
        this.repository = repository;
        this.service = service;
    }

    public Service getService(){
        return this.service;
    }

    @PostMapping("/create")
    public ResponseEntity<Entity> create(@RequestBody Entity t) {

        t.setId(null);

        Entity response = getService().create(t);
        return (response != null) ? ResponseEntity.status(HttpStatus.CREATED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Entity> update(@PathVariable("id") Long id, @RequestBody Entity t) {

        if (Objects.isNull(t.getId()))
            t.setId(id);

        Entity response = getService().update(t).orElse(null);
        return (response != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Entity> delete(@PathVariable("id") Long id) {

        Entity response = getService().delete(id).orElse(null);
        return (response != null) ? ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response) : ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Entity> get(@PathVariable("id") Long id) {

        Entity response = getService().get(id).orElse(null);
        return (response != null) ? ResponseEntity.status(HttpStatus.OK).body(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("/getall")
    public ResponseEntity<Iterator<Entity>> getAll() {

        Iterator<Entity> response = getService().getAll();
        return (response != null) ? ResponseEntity.status(HttpStatus.OK).body(response) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
