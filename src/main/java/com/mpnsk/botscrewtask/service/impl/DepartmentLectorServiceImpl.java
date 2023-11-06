package com.mpnsk.botscrewtask.service.impl;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.repository.DepartmentLectorRepository;
import com.mpnsk.botscrewtask.service.DepartmentLectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentLectorServiceImpl implements DepartmentLectorService {
    private final DepartmentLectorRepository departmentLectorRepository;

    @Override
    public DepartmentLector add(DepartmentLector departmentLector) {
        return departmentLectorRepository.save(departmentLector);
    }

    @Override
    public List<DepartmentLector> getDepartmentLectors(Department department) {
        return departmentLectorRepository.getDepartmentLectors(department);
    }
}
