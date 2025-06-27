package com.CCMS.service;

import com.CCMS.model.User;
import com.CCMS.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserRepository> {

    public UserService(UserRepository userRepository){
        super(userRepository);
    }

}