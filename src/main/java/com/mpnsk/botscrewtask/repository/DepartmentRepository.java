package com.mpnsk.botscrewtask.repository;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.Lector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Optional<Department> findDepartmentByName(String departmentName);

    @Query("SELECT d.headOfDepartment FROM Department d WHERE d = :department")
    Lector getHeadOfDepartment(@Param("department") Department department);

    @Query(nativeQuery = true, value = "SELECT l.degree, COUNT(*) as count FROM lectors l WHERE l.id IN " +
            "(SELECT dl.lector_id FROM department_lector dl WHERE dl.department_id = :departmentId) " +
            "GROUP BY l.degree")
    List<Tuple> getDepartmentStatisticsById(@Param("departmentId") Long departmentId);
}
