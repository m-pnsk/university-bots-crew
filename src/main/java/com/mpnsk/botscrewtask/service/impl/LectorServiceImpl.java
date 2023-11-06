package com.mpnsk.botscrewtask.service.impl;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.Lector;
import com.mpnsk.botscrewtask.repository.LectorRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import com.mpnsk.botscrewtask.service.LectorService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    @Override
    public Lector add(Lector lector) {
        return lectorRepository.save(lector);
    }

    @Override
    public Lector getById(Long id) {
        return lectorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found lector with id: " + id));
    }

    @Override
    public Optional<Lector> getFirst() {
        return lectorRepository.findFirst();
    }

    @Override
    public BigDecimal getLectorSalary(Lector lector) {
        return lectorRepository.getLectorSalaryById(lector.getId());
    }

    @Override
    public List<Lector> globalSearchByTemplate(String template) {
        return lectorRepository.findLectorsByTemplate(template);
    }

    @Override
    public List<Department> getDepartments(Lector lector) {
        return lectorRepository.getDepartmentsById(lector.getId());
    }

}
