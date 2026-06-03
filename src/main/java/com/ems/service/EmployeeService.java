package com.ems.service;

import com.ems.dto.EmployeeRequestDTO;
import com.ems.dto.EmployeeResponseDTO;
import com.ems.dto.DepartmentCountDTO;
import java.util.List;
import com.ems.dto.EmployeeStatsDTO;
public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO request);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getEmployees(
            int page,
            int size,
            String sortBy,
            String direction);

    List<EmployeeResponseDTO> getEmployeesByDepartment(String department);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request);

    void deleteEmployee(Long id);
    EmployeeStatsDTO getEmployeeStats();
    List<DepartmentCountDTO> getDepartmentCounts();
}