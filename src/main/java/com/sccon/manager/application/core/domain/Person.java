package com.sccon.manager.application.core.domain;

import java.time.LocalDate;

public class Person {

    private Long id;
    private String name;
    private LocalDate birth;
    private LocalDate admission;

    public Person(Long id, String name, LocalDate birth, LocalDate admission) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.admission = admission;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public LocalDate getAdmission() {
        return admission;
    }

    public void setAdmission(LocalDate admission) {
        this.admission = admission;
    }
}
