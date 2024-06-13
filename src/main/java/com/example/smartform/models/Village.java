package com.example.smartform.models;

import jakarta.persistence.*;

@Entity
@Table
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "village_generator")
    @SequenceGenerator(name = "village_generator", sequenceName = "village_seq", allocationSize = 1)
    private Long id;
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
