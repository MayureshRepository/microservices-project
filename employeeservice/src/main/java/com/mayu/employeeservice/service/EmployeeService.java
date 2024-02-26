package com.mayu.employeeservice.service;

import com.mayu.employeeservice.dto.APIResponseDto;
import com.mayu.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto getEmployees(EmployeeDto employeeDto);

    APIResponseDto getEmployeeByEmail(String email);

    APIResponseDto getEmployeeById(Long id);

}
