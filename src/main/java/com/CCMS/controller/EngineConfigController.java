package com.CCMS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.EngineConfig;
import com.CCMS.repository.EngineConfigRepository;
import com.CCMS.service.EngineConfigService;

@RestController
@RequestMapping(path = "/engine")
public class EngineConfigController extends BaseController<EngineConfig, EngineConfigRepository, EngineConfigService>{

    public EngineConfigController(EngineConfigRepository repository, EngineConfigService service) {
        super(repository, service);
    }

}

