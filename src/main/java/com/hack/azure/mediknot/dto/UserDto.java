package com.hack.azure.mediknot.dto;

import com.hack.azure.mediknot.entity.Address;
import com.hack.azure.mediknot.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    private Integer id;
    private String name;
    private String emailId;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Integer age;
    private Address address;
    private String aadharNumber;
    private String profileImageUrl;

}