package com.mpnsk.botscrewtask.dto;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.Lector;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class LectorDto {
    private String fullName;
    private Lector.Degree degree;
    private BigDecimal salary;
    private List<Department> departments;

    @Override
    public String toString() {
        return "Lector {"
                + "\n\tfull name='" + fullName + '\''
                + ",\n\tdegree=" + degree
                + ",\n\tsalary=" + salary
                + ",\n\tdepartments=" + departments
                + "\n}";
    }
}
