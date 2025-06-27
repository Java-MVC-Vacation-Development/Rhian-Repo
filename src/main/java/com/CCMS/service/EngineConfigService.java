package com.CCMS.service;

import com.CCMS.model.EngineConfig;
import com.CCMS.repository.EngineConfigRepository;

import org.springframework.stereotype.Service;

@Service
public class EngineConfigService extends BaseService<EngineConfig, EngineConfigRepository> {

    public EngineConfigService(EngineConfigRepository repository){
        super(repository);
    }

}
