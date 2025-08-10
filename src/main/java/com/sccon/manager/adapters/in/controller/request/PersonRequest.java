package com.sccon.manager.adapters.in.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonRequest {

    private Long id;
    private String name;
    private LocalDate birth;
    private LocalDate admission;
}
