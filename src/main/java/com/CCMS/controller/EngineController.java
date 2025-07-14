package com.CCMS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.Engine;
import com.CCMS.repository.EngineRepository;
import com.CCMS.service.EngineService;

@RestController
@RequestMapping(path = "/engine")
public class EngineController extends BaseController<Engine, EngineRepository, EngineService>{

    public EngineController(EngineRepository engineRepository, EngineService engineService){
        super(engineRepository, engineService);
    }

}