package com.mpnsk.botscrewtask.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.model.Lector;
import com.mpnsk.botscrewtask.repository.LectorRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LectorServiceImplTest {
    private static final Long LECTOR_ID = 1L;
    private static final String LECTOR_NAME = "Name";
    private static final String LECTOR_SURNAME = "Surname";
    private static final Lector.Degree LECTOR_DEGREE = Lector.Degree.PROFESSOR;

    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorServiceImpl lectorService;
    private Lector lector;

    @BeforeEach
    void setup() {
        lector = new Lector();
        lector.setId(LECTOR_ID);
        lector.setName(LECTOR_NAME);
        lector.setSurName(LECTOR_SURNAME);
        lector.setDegree(LECTOR_DEGREE);
        lector.setSalaryInDepartment(List.of(
                new DepartmentLector(lector, new Department().setId(1L), BigDecimal.valueOf(10000)),
                new DepartmentLector(lector, new Department().setId(2L), BigDecimal.valueOf(5000))
        ));
    }

    @Test
    void add_ok() {
        when(lectorRepository.save(any(Lector.class))).thenReturn(lector);

        Lector savedLector = lectorService.add(lector);

        assertNotNull(savedLector);
        assertEquals(LECTOR_ID, savedLector.getId());
        assertEquals(LECTOR_NAME, savedLector.getName());
        assertEquals(LECTOR_SURNAME, savedLector.getSurName());
        assertEquals(LECTOR_DEGREE, savedLector.getDegree());
    }

    @Test
    void getLectorSalary_ok() {
        when(lectorRepository.getLectorSalaryById(any(Long.class))).thenReturn(BigDecimal.valueOf(15000));

        BigDecimal actualSalary = lectorService.getLectorSalary(lector);
        BigDecimal expectedSalary = BigDecimal.valueOf(lector.getSalaryInDepartment().stream()
                .mapToInt(dl -> dl.getSalary().intValue()).sum());

        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void getLectorSalary_notOk() {
        when(lectorRepository.getLectorSalaryById(any(Long.class))).thenReturn(BigDecimal.valueOf(12000));

        BigDecimal actualSalary = lectorService.getLectorSalary(lector);
        BigDecimal expectedSalary = BigDecimal.valueOf(lector.getSalaryInDepartment().stream()
                .mapToInt(dl -> dl.getSalary().intValue()).sum());

        assertNotEquals(expectedSalary, actualSalary);
    }

    @Test
    void getDepartments_ok() {
        when(lectorRepository.getDepartmentsById(any(Long.class))).thenReturn(
                List.of(
                        new Department().setId(1L),
                        new Department().setId(2L)
                )
        );

        List<Department> actualDepartments = lectorService.getDepartments(lector);
        List<Department> expectedDepartments = lector.getSalaryInDepartment().stream()
                .map(DepartmentLector::getDepartment).toList();
        assertEquals(expectedDepartments, actualDepartments);
    }

    @Test
    void getDepartments_notOk() {
        when(lectorRepository.getDepartmentsById(any(Long.class))).thenReturn(
                List.of(
                        new Department().setId(1L),
                        new Department().setId(3L)
                )
        );

        List<Department> actualDepartments = lectorService.getDepartments(lector);
        List<Department> expectedDepartments = lector.getSalaryInDepartment().stream()
                .map(DepartmentLector::getDepartment).toList();
        assertNotEquals(expectedDepartments, actualDepartments);
    }
}