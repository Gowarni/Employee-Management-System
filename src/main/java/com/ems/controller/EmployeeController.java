package com.ems.controller;

import com.ems.dto.EmployeeRequestDTO;
import com.ems.dto.EmployeeResponseDTO;
import com.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ems.dto.EmployeeStatsDTO;
import com.ems.dto.DepartmentCountDTO;
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDTO createEmployee(
            @Valid @RequestBody EmployeeRequestDTO request) {

        return employeeService.createEmployee(request);
    }
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO request) {

        return employeeService.updateEmployee(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully";
    }
    @GetMapping("/search")
    public List<EmployeeResponseDTO> searchByDepartment(
            @RequestParam String department) {

        return employeeService.getEmployeesByDepartment(department);
    }
    @GetMapping("/paged")
    public List<EmployeeResponseDTO> getEmployeesPaged(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getEmployees(
                page,
                size,
                sortBy,
                direction);
    }
    @GetMapping("/stats")
    public EmployeeStatsDTO getStats() {

        return employeeService.getEmployeeStats();
    }
    @GetMapping("/department-count")
    public List<DepartmentCountDTO> getDepartmentCounts() {
        return employeeService.getDepartmentCounts();
    }
}