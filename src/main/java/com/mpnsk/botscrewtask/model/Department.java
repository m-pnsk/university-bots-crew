package com.mpnsk.botscrewtask.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@EqualsAndHashCode
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Lector headOfDepartment;
    @OneToMany(mappedBy = "department")
    private List<DepartmentLector> departmentLectors;

    public Department() {
    }

    public Department(String name, Lector headOfDepartment) {
        this.name = name.toUpperCase();
        this.headOfDepartment = headOfDepartment;
        this.departmentLectors = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "\n\t\tDepartment {"
                + "name='" + name + '\''
                + ", headOfDepartment=" + headOfDepartment
                + "}";
    }
}
