package com.CCMS.controller;

import com.CCMS.model.BaseEntity;
import com.CCMS.service.BaseService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.health.Status;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;


@RestController
@ApplicationScope
public class BaseController<T extends BaseEntity> {

    @Autowired
    BaseService<T> service;

    @PostMapping
    public ResponseBody create(T t) {
        return ResponseBody.status(Status.CREATED).entity(categoryService.create(category)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(Category category, @PathParam("id") Long id) {
        if (Objects.isNull(category.getId()))
            category.setId(id);
        return categoryService.update(category).map(i -> Response.status(Status.ACCEPTED).entity(i).build())
                .orElseGet(() -> Response.status(Status.NOT_FOUND).build());
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        categoryService.delete(id);
        return Response.status(Status.ACCEPTED).build();
    }
    
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        return categoryService.get(id).map(i -> Response.status(Status.OK).entity(i).build())
                .orElseGet(() -> Response.status(Status.NO_CONTENT).build());
    }
    
    @GET
    public Response get() {
        return Response.status(Status.OK).entity(categoryService.getAll()).build();
    }
}