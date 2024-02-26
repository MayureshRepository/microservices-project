package com.mayu.employeeservice.service;

import com.mayu.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080/" , value ="DEPARTMENT-SERVICE")
@FeignClient(value = "DEPARTMENT-SERVICE")
public interface ApiClient {
    //We are taking this method from Department Controller
    @GetMapping("api/departments/{getDepartment}")
    DepartmentDto getDepartmentData(@PathVariable("getDepartment") String code);


}
