package com.ems.repository;

import com.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department);
    Optional<Employee> findByEmail(String email);
    long count();

    long countDistinctByDepartmentIsNotNull();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    @Query("SELECT MAX(e.salary) FROM Employee e")
    Double getHighestSalary();

    @Query("SELECT MIN(e.salary) FROM Employee e")
    Double getLowestSalary();

    @Query("""
       SELECT e.department, COUNT(e)
       FROM Employee e
       GROUP BY e.department
       """)
    List<Object[]> getDepartmentWiseCount();
}

