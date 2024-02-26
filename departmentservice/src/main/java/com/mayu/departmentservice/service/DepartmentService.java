package com.mayu.departmentservice.service;

import com.mayu.departmentservice.dto.DepartmentDto;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
