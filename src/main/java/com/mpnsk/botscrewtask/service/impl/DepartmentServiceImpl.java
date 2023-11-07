package com.mpnsk.botscrewtask.service.impl;

import com.mpnsk.botscrewtask.dto.DepartmentStatisticsDto;
import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.model.Lector;
import com.mpnsk.botscrewtask.repository.DepartmentRepository;
import com.mpnsk.botscrewtask.service.DepartmentLectorService;
import com.mpnsk.botscrewtask.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentLectorService departmentLectorService;


    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found department with id: " + id));
    }

    @Override
    public Department getByName(String departmentName) {
        return departmentRepository.findDepartmentByName(departmentName.toUpperCase()).orElseThrow(
                () -> new NoSuchElementException("Not found department with name: " + departmentName));
    }

    @Override
    public Lector getHeadOfDepartment(Department department) {
        Lector headOfDepartment = department.getHeadOfDepartment();
        if (headOfDepartment != null) {
            return headOfDepartment;
        }
        return departmentRepository.getHeadOfDepartment(department);
    }

    @Override
    public BigDecimal calculateAvgSalary(Department department) {
        List<BigDecimal> departmentSalary = departmentLectorService.getDepartmentLectors(department).stream()
                .map(DepartmentLector::getSalary).toList();
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal salary : departmentSalary) {
            total = total.add(salary);
        }
        return total.divide(BigDecimal.valueOf(departmentSalary.size()), RoundingMode.HALF_UP)
                .setScale(2,  RoundingMode.HALF_UP);
    }

    @Override
    public int getCountOfEmployee(Department department) {
        return departmentLectorService.getDepartmentLectors(department).size();
    }

    @Override
    public String showDepartmentStatistics(Department department) {
        return departmentRepository.getDepartmentStatisticsById(department.getId()).stream()
                .map(tuple -> new DepartmentStatisticsDto(
                        Lector.Degree.valueOf(tuple.get(0, String.class)),
                        tuple.get(1, BigInteger.class).intValue()
                )).toList().toString();
    }
}
