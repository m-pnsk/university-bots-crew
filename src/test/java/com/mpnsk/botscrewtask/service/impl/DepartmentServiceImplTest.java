package com.mpnsk.botscrewtask.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.model.Lector;
import com.mpnsk.botscrewtask.repository.DepartmentRepository;
import com.mpnsk.botscrewtask.service.DepartmentLectorService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private static final Long DEPARTMENT_ID = 1L;
    private static final String DEPARTMENT_NAME = "Name";

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private DepartmentLectorService departmentLectorService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setup() {
        department = new Department();
        department.setId(DEPARTMENT_ID);
        department.setName(DEPARTMENT_NAME);
        department.setDepartmentLectors(List.of(
                new DepartmentLector(new Lector().setId(1L), department, BigDecimal.valueOf(10000)),
                new DepartmentLector(new Lector().setId(2L), department, BigDecimal.valueOf(5000))
        ));
        department.setHeadOfDepartment(new Lector().setId(1L));
    }

    @Test
    void add_ok() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department savedDepartment = departmentService.add(department);

        assertNotNull(savedDepartment);
        assertEquals(DEPARTMENT_ID, savedDepartment.getId());
        assertEquals(DEPARTMENT_NAME, savedDepartment.getName());
        assertEquals(department.getHeadOfDepartment(), savedDepartment.getHeadOfDepartment());
        assertEquals(department.getDepartmentLectors(), savedDepartment.getDepartmentLectors());
    }

    @Test
    void getByName_ok() {
        when(departmentRepository.findByName(any(String.class))).thenReturn(Optional.of(department));

        Department actual = departmentService.getByName(DEPARTMENT_NAME);
        Department expected = department;

        assertEquals(expected, actual);
    }

    @Test
    void getByName_notOk() {
        when(departmentRepository.findByName(any(String.class))).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> departmentService.getByName(DEPARTMENT_NAME));
    }

    @Test
    void getHeadOfDepartment_headOfDepartmentNotInitialized_ok() {
        when(departmentRepository.getHeadOfDepartment(any(Department.class))).thenReturn(department.getHeadOfDepartment());

        Department testDepartment = new Department().setId(department.getId())
                .setName(department.getName())
                .setDepartmentLectors(department.getDepartmentLectors());
        Lector actual = departmentService.getHeadOfDepartment(testDepartment);
        Lector expected = department.getHeadOfDepartment();

        assertEquals(expected, actual);
    }

    @Test
    void getHeadOfDepartment_ok() {
        Lector expected = department.getHeadOfDepartment();
        Lector actual = departmentService.getHeadOfDepartment(department);

        assertEquals(expected, actual);
    }

    @Test
    void calculateAvgSalary_ok() {
        when(departmentLectorService.getDepartmentLectors(any(Department.class))).thenReturn(department.getDepartmentLectors());

        BigDecimal expectedAvg = BigDecimal.valueOf(department.getDepartmentLectors().stream()
                .mapToInt(dl -> dl.getSalary().intValue()).average().orElse(0)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualAvg = departmentService.calculateAvgSalary(department);

        assertEquals(expectedAvg, actualAvg);
    }

    @Test
    void getCountOfEmployee() {
        when(departmentLectorService.getDepartmentLectors(any(Department.class))).thenReturn(department.getDepartmentLectors());

        int expected = department.getDepartmentLectors().size();
        int actual = departmentService.getCountOfEmployee(department);

        assertEquals(expected, actual);
    }
}