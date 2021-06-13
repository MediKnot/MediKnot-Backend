package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.hack.azure.mediknot.enums.Gender;

import java.time.LocalDate;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String emailId;
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;
    private Integer age;

    @Embedded
    private Address address;

    private String aadharNumber;
    private String profileImageUrl;

    public User(String name, String emailId, String phoneNumber, Gender gender, LocalDate dateOfBirth, Integer age, Address address, String aadharNumber, String profileImageUrl) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.address = address;
        this.aadharNumber = aadharNumber;
        this.profileImageUrl =profileImageUrl;
    }
}
