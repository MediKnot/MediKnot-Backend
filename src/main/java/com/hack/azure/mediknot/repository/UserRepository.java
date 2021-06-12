package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    public Boolean existsByEmailId(String emailId);

    public User findByEmailId(String emailId);

}