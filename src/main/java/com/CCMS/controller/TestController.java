package com.CCMS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.Test;
import com.CCMS.repository.TestRepository;
import com.CCMS.service.TestService;

@RestController
public class TestController extends BaseController<Test, TestRepository, TestService>{

    public TestController(TestRepository testRepository, TestService testService){
        super(testRepository, testService);
    }
    
    @GetMapping("/teste")
    public ResponseEntity<Test> dcfgdgfd() {
        return ResponseEntity.status(HttpStatus.OK).body(new Test());
    }

}
