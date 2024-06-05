package com.excel.ExcelDatabase.Repository;


import com.excel.ExcelDatabase.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
