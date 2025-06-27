package com.CCMS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMS.model.User;
import com.CCMS.repository.UserRepository;
import com.CCMS.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController extends BaseController<User, UserRepository, UserService>{

    public UserController(UserRepository repository, UserService service) {
        super(repository, service);
    }

}
