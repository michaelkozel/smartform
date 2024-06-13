package com.example.smartform.models;

import jakarta.persistence.*;

@Entity
@Table
public class VillagePart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "village_part_generator")
    @SequenceGenerator(name = "village_part_generator", sequenceName = "village_part_seq", allocationSize = 1)

    private Long id;
    private String villageCode;
    private String code;
    private String villagePartName;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVillagePartName() {
        return villagePartName;
    }

    public void setVillagePartName(String villagePartName) {
        this.villagePartName = villagePartName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
