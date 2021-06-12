package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PatientService patientService;

    public UserServiceImpl(UserRepository userRepository, PatientService patientService) {
        this.userRepository = userRepository;
        this.patientService = patientService;
    }

    @Override
    public User createUser(User user) throws UserException{
        if(userRepository.existsByEmailId(user.getEmailId())){
            throw new UserException("User with emailId exists.", 409);
        }
        User createdUser = patientService.createPatient(user);
        return  createdUser;
    }
}
