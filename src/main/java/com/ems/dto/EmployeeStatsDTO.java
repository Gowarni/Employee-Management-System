package com.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStatsDTO {

    private Long totalEmployees;
    private Long totalDepartments;
    private Double averageSalary;
    private Double highestSalary;
    private Double lowestSalary;
}