package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Report {

    private String name;
    private LocalDate date;

    @Embedded
    private Address lab;

    private String reportUrl;

    public Report(String name, LocalDate date, Address lab, String reportUrl) {
        this.name = name;
        this.date = date;
        this.lab = lab;
        this.reportUrl = reportUrl;
    }
}
