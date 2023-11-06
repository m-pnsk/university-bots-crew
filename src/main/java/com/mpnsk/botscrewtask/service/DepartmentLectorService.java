package com.mpnsk.botscrewtask.service;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import java.util.List;

public interface DepartmentLectorService {
    DepartmentLector add(DepartmentLector departmentLector);
    List<DepartmentLector> getDepartmentLectors(Department department);
}
