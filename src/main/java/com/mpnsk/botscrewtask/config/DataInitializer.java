package com.mpnsk.botscrewtask.config;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.model.DepartmentLector;
import com.mpnsk.botscrewtask.model.Lector;
import com.mpnsk.botscrewtask.service.DepartmentLectorService;
import com.mpnsk.botscrewtask.service.DepartmentService;
import com.mpnsk.botscrewtask.service.LectorService;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class DataInitializer {
    private final LectorService lectorService;
    private final DepartmentService departmentService;
    private final DepartmentLectorService departmentLectorService;

    @PostConstruct
    public void inject() {
        if (lectorService.getFirst().isEmpty()) {
            Lector mykolaPanasiuk = lectorService.add(new Lector("Mykola", "Panasiuk", Lector.Degree.ASSISTANT));
            Lector olegIlarionov = lectorService.add(new Lector("Oleg", "Ilarionov", Lector.Degree.ASSOCIATE_PROFESSOR));
            Lector bobBobovych = lectorService.add(new Lector("Bob", "Bobovych", Lector.Degree.PROFESSOR));
            Lector aliceSmith = lectorService.add(new Lector("Alice", "Smith", Lector.Degree.ASSISTANT));
            Lector johnDeni = lectorService.add(new Lector("John", "Deni", Lector.Degree.ASSOCIATE_PROFESSOR));

            Department fit = departmentService.add(new Department("FIT", olegIlarionov));
            Department knkb = departmentService.add(new Department("KNKB", bobBobovych));
            Department fg = departmentService.add(new Department("FG", johnDeni));

            departmentLectorService.add(new DepartmentLector(olegIlarionov, fit, BigDecimal.valueOf(17000)));
            departmentLectorService.add(new DepartmentLector(bobBobovych, knkb, BigDecimal.valueOf(19000)));
            departmentLectorService.add(new DepartmentLector(johnDeni, fg, BigDecimal.valueOf(15000)));
            departmentLectorService.add(new DepartmentLector(mykolaPanasiuk, fit, BigDecimal.valueOf(10000)));
            departmentLectorService.add(new DepartmentLector(aliceSmith, fg, BigDecimal.valueOf(9000)));
            departmentLectorService.add(new DepartmentLector(mykolaPanasiuk, knkb, BigDecimal.valueOf(11000)));
            departmentLectorService.add(new DepartmentLector(bobBobovych, fit, BigDecimal.valueOf(5000)));
        }
    }
}
