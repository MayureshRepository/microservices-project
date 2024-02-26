package com.mayu.departmentservice.mapper;

import com.mayu.departmentservice.dto.DepartmentDto;
import com.mayu.departmentservice.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getGetDepartmentDescription(),
                department.getGetDepartmentCode()
        );
        return departmentDto;
    }


    public static Department mapToEntity(DepartmentDto departmentDto){
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getGetDepartmentDescription(),
                departmentDto.getGetDepartmentCode()
        );

        return department;
    }




}
