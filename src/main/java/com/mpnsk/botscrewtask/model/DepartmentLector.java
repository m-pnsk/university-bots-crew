package com.mpnsk.botscrewtask.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "department_lector")
public class DepartmentLector {
    @EmbeddedId
    private DepartmentLectorKey id;
    @ManyToOne
    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    Department department;


    @ManyToOne
    @MapsId("lectorId")
    @JoinColumn(name = "lector_id")
    Lector lector;
    private BigDecimal salary;

    public DepartmentLector() {}

    public DepartmentLector(Lector lector, Department department, BigDecimal salary) {
        this.id = new DepartmentLectorKey(department.getId(), lector.getId());
        this.department = department;
        this.lector = lector;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentLector that = (DepartmentLector) o;
        return Objects.equals(id, that.id) && Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salary);
    }
}
