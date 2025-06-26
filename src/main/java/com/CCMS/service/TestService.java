package com.CCMS.service;

import org.springframework.stereotype.Service;

import com.CCMS.model.Test;
import com.CCMS.repository.TestRepository;

@Service
public class TestService extends BaseService<Test, TestRepository>{

    public TestService(TestRepository testRepository){
        super(testRepository);
    }
    
}
