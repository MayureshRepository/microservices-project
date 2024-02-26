package com.mayu.employeeservice.mapper;

import com.mayu.employeeservice.dto.DepartmentDto;
import com.mayu.employeeservice.dto.EmployeeDto;
import com.mayu.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getGetDepartmentCode(),
                employee.getOrganisationCode()
        );

        return  employeeDto;
    }


    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getGetDepartmentCode(),
                employeeDto.getOrganisationCode()
        );

        return employee;
    }

}
