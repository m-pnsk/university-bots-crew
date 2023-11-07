package com.mpnsk.botscrewtask.repository;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.model.DepartmentLectorKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentLectorRepository extends CrudRepository<DepartmentLector, DepartmentLectorKey> {
    List<DepartmentLector> findAllByDepartment(Department department);
}
