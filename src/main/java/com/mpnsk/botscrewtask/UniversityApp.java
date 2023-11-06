package com.mpnsk.botscrewtask;

import com.mpnsk.botscrewtask.model.Department;
import com.mpnsk.botscrewtask.service.DepartmentService;
import com.mpnsk.botscrewtask.service.LectorService;
import com.mpnsk.botscrewtask.service.mapper.LectorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;
import java.util.Scanner;

@AllArgsConstructor
@Component
public class UniversityApp {
    LectorService lectorService;
    DepartmentService departmentService;
    LectorMapper lectorMapper;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                \n
                Choose an option:
                1. Who is head of department?
                2. Show statistics for department
                3. Show the average salary for the department
                4. Show count of employee for department
                5. Global search for a lecturer
                Any another key: close program
                """);
        char option = scanner.next().charAt(0);
        System.out.println(
                switch (option) {
                    case '1' -> getHeadOfDepartment(getDepartmentByName());
                    case '2' -> showDepartmentStatistics(getDepartmentByName());
                    case '3' -> showAvgSalaryForTheDepartment(getDepartmentByName());
                    case '4' -> showCountOfEmployeeForDepartment(getDepartmentByName());
                    case '5' -> globalSearchByTemplate();
                    default -> "Program is closing...";
                });
        if (option <= '5' && option >= '1') {
            run();
        }
    }

    private String getHeadOfDepartment(Department department) {
        return String.format("Head of %s department is %s", department.getName(),
                departmentService.getHeadOfDepartment(department));
    }

    private String showDepartmentStatistics(Department department) {
        return departmentService.showDepartmentStatistics(department);
    }

    private String showAvgSalaryForTheDepartment(Department department) {
        return String.format("The average salary of %s is %s", department.getName(),
                departmentService.calculateAvgSalary(department));
    }

    private String showCountOfEmployeeForDepartment(Department department) {
        return String.format("Number of employees in the %s department = %s", department.getName(),
                departmentService.getCountOfEmployee(department));
    }

    private String globalSearchByTemplate() {
        System.out.print("Input search template: ");
        Scanner scanner = new Scanner(System.in);
        String template = scanner.nextLine();
        return lectorService.globalSearchByTemplate(template).stream()
                .map(lectorMapper::objectToDto)
                .toList().toString();
    }

    private Department getDepartmentByName() {
        System.out.print("Input department name: ");
        Department department;
        try {
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            department = departmentService.getByName(name);
        } catch (NoSuchElementException e) {
            System.out.println("Not found department with this name");
            department = getDepartmentByName();
        }
        return department;
    }

}
