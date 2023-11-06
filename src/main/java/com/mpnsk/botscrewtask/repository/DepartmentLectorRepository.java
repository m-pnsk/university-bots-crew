package com.mpnsk.botscrewtask.repository;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.model.DepartmentLectorKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentLectorRepository extends CrudRepository<DepartmentLector, DepartmentLectorKey> {
    @Query("SELECT dl FROM DepartmentLector dl JOIN dl.department WHERE dl.department = :department")
    List<DepartmentLector> getDepartmentLectors(@Param("department") Department department);
}
