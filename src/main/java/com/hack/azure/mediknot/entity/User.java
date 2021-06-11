package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {

    enum Gender{
        MALE, FEMALE, OTHERS
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String emailId;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Integer age;

    @Embedded
    private Address address;

    private String aadharNumber;

    public User(String name, String emailId, String phoneNumber, Gender gender, LocalDate dateOfBirth, Integer age, Address address, String aadharNumber) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.address = address;
        this.aadharNumber = aadharNumber;
    }
}
