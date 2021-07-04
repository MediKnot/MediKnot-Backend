package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileViewsDto {

    private Integer id;
    private LocalDateTime timestamp;

    private String viewersName;

    private String viewersEmail;

}
