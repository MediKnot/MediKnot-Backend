package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class Address {

    private String street;
    private String city;
    private String state;
    private Integer pinCode;

    public Address(String street, String city, String state, Integer pinCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }
}
