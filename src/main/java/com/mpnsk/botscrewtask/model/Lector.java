package com.mpnsk.botscrewtask.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@EqualsAndHashCode
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surName;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    @OneToMany(mappedBy = "lector")
    private List<DepartmentLector> salaryInDepartment;

    public Lector() {
    }

    public Lector(String name, String surName, Degree degree) {
        this.name = name;
        this.surName = surName;
        this.degree = degree;
        this.salaryInDepartment = new ArrayList<>();
    }

    public enum Degree {
        ASSISTANT,
        ASSOCIATE_PROFESSOR,
        PROFESSOR
    }

    @Override
    public String toString() {
        return "Lector {"
                + "name='" + name + '\''
                + ", surName='" + surName + '\''
                + ", degree=" + degree
                + '}';
    }
}
