package com.ems.service;

import com.ems.dto.EmployeeRequestDTO;
import com.ems.dto.EmployeeResponseDTO;
import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.ems.exception.EmployeeAlreadyExistsException;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.dto.EmployeeStatsDTO;
import com.ems.dto.DepartmentCountDTO;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {

        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .salary(request.getSalary())
                .joiningDate(request.getJoiningDate())
                .build();
        employeeRepository.findByEmail(request.getEmail())
                .ifPresent(existingEmployee -> {
                    throw new EmployeeAlreadyExistsException(
                            "Employee already exists with email: " + request.getEmail());
                });
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponseDTO.builder()
                .id(savedEmployee.getId())
                .firstName(savedEmployee.getFirstName())
                .lastName(savedEmployee.getLastName())
                .email(savedEmployee.getEmail())
                .department(savedEmployee.getDepartment())
                .salary(savedEmployee.getSalary())
                .joiningDate(savedEmployee.getJoiningDate())
                .build();
    }
    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employee -> EmployeeResponseDTO.builder()
                        .id(employee.getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .department(employee.getDepartment())
                        .salary(employee.getSalary())
                        .joiningDate(employee.getJoiningDate())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .build();
    }
    @Override
    public EmployeeResponseDTO updateEmployee(Long id,
                                              EmployeeRequestDTO request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setJoiningDate(request.getJoiningDate());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeResponseDTO.builder()
                .id(updatedEmployee.getId())
                .firstName(updatedEmployee.getFirstName())
                .lastName(updatedEmployee.getLastName())
                .email(updatedEmployee.getEmail())
                .department(updatedEmployee.getDepartment())
                .salary(updatedEmployee.getSalary())
                .joiningDate(updatedEmployee.getJoiningDate())
                .build();
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }
    @Override
    public List<EmployeeResponseDTO> getEmployeesByDepartment(String department) {

        return employeeRepository.findByDepartment(department)
                .stream()
                .map(employee -> EmployeeResponseDTO.builder()
                        .id(employee.getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .department(employee.getDepartment())
                        .salary(employee.getSalary())
                        .joiningDate(employee.getJoiningDate())
                        .build())
                .toList();
    }
    @Override
    public List<EmployeeResponseDTO> getEmployees(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<Employee> employeePage =
                employeeRepository.findAll(pageable);

        return employeePage.getContent()
                .stream()
                .map(employee -> EmployeeResponseDTO.builder()
                        .id(employee.getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .department(employee.getDepartment())
                        .salary(employee.getSalary())
                        .joiningDate(employee.getJoiningDate())
                        .build())
                .toList();
    }
    @Override
    public EmployeeStatsDTO getEmployeeStats() {

        return EmployeeStatsDTO.builder()
                .totalEmployees(employeeRepository.count())
                .totalDepartments(
                        employeeRepository.countDistinctByDepartmentIsNotNull()
                )
                .averageSalary(
                        employeeRepository.getAverageSalary()
                )
                .highestSalary(
                        employeeRepository.getHighestSalary()
                )
                .lowestSalary(
                        employeeRepository.getLowestSalary()
                )
                .build();
    }
    @Override
    public List<DepartmentCountDTO> getDepartmentCounts() {

        return employeeRepository.getDepartmentWiseCount()
                .stream()
                .map(result -> new DepartmentCountDTO(
                        (String) result[0],
                        (Long) result[1]
                ))
                .toList();
    }
}