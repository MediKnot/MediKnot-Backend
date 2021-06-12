package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws UserException{
        if(userRepository.existsByEmailId(user.getEmailId())){
            throw new UserException("User with emailId exists.", 409);
        }
        return userRepository.save(user);
    }
}
