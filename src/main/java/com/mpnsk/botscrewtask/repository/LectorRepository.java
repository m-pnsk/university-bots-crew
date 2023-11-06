package com.mpnsk.botscrewtask.repository;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.Lector;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends CrudRepository<Lector, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM lectors LIMIT 1")
    Optional<Lector> findFirst();
    @Query(nativeQuery = true,
            value = "SELECT SUM(salary) FROM department_lector WHERE lector_id = :lectorId")
    BigDecimal getLectorSalaryById(@Param("lectorId") Long lectorId);

    @Query("SELECT l FROM Lector l WHERE CONCAT(l.name,' ', l.surName) LIKE CONCAT('%',:template,'%')")
    List<Lector> findLectorsByTemplate(@Param("template") String template);

    @Query("SELECT d FROM Department d JOIN d.departmentLectors dl WHERE dl.id.lectorId = :lectorId")
    List<Department> getDepartmentsById(@Param("lectorId") Long lectorId);
}
