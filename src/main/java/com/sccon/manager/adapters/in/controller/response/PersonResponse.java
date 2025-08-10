package com.sccon.manager.adapters.in.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonResponse {

    private Long id;
    private String name;
    private LocalDate birth;
    private LocalDate admission;
}
