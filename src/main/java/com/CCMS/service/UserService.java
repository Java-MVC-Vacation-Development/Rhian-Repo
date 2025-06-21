package com.CCMS.service;

import com.CCMS.model.User;
import com.CCMS.repository.BaseRepository;
import com.CCMS.repository.UserRepository;

import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
public class UserService implements BaseService<User> {

    UserRepository userRepository;

    @Override
    public BaseRepository<User> getRepository() {
        return userRepository;
    }

}