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
    private String country;
    private Integer pinCode;
    private Float latitude;
    private Float longitude;

    public Address(String street, String city, String state, String country, Integer pinCode, Float latitude, Float longitude ) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
