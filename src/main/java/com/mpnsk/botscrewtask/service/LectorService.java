package com.mpnsk.botscrewtask.service;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.Lector;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LectorService {
    Lector add(Lector lector);
    Lector getById(Long id);
    Optional<Lector> getFirst();
    BigDecimal getLectorSalary(Lector lector);
    List<Lector> globalSearchByTemplate(String template);

    List<Department> getDepartments(Lector lector);
}
