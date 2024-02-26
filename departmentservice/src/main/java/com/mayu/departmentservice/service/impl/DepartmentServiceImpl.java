package com.mayu.departmentservice.service.impl;

import com.mayu.departmentservice.dto.DepartmentDto;
import com.mayu.departmentservice.entity.Department;
import com.mayu.departmentservice.mapper.DepartmentMapper;
import com.mayu.departmentservice.service.DepartmentService;
import com.mayu.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

//        Department department = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getGetDepartmentDescription(),
//                departmentDto.getGetDepartmentCode()
//
//        );

        Department department = DepartmentMapper.mapToEntity(departmentDto);

        Department saveDepartment = departmentRepository.save(department);

//        DepartmentDto savedDepartmentDto = new DepartmentDto(
//                saveDepartment.getId(),
//                saveDepartment.getDepartmentName(),
//                saveDepartment.getGetDepartmentDescription(),
//                saveDepartment.getGetDepartmentCode()
//        );

        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(saveDepartment);


        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department= departmentRepository.findBygetDepartmentCode(code);

     //   System.out.println(department);

//        DepartmentDto departmentDto = new DepartmentDto(
//                department.getId(),
//                department.getDepartmentName(),
//                department.getGetDepartmentDescription(),
//                department.getGetDepartmentCode()
//        );

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;


    }
}
