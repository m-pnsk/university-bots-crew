package com.mpnsk.botscrewtask.service;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.Lector;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface DepartmentService {
    Department add(Department department);
    Department getById(Long id);

    Department getByName(String departmentName);
    String showDepartmentStatistics(Department department);
    Lector getHeadOfDepartment(Department department);

    BigDecimal calculateAvgSalary(Department department);

    int getCountOfEmployee(Department department);
}
