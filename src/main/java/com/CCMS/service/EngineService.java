package com.CCMS.service;

import org.springframework.stereotype.Service;

import com.CCMS.model.Engine;
import com.CCMS.repository.EngineRepository;

@Service
public class EngineService extends BaseService<Engine, EngineRepository>{

    public EngineService(EngineRepository repository) {
        super(repository);
    }

}
