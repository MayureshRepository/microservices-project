package com.mayu.employeeservice.controller;

import com.mayu.employeeservice.dto.APIResponseDto;
import com.mayu.employeeservice.dto.EmployeeDto;
import com.mayu.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeDto> getEmployees(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 =employeeService.getEmployees(employeeDto);
        return new ResponseEntity<>(employeeDto1 , HttpStatus.CREATED);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<APIResponseDto> getEmployeeByEmail(@PathVariable(name="email") String email){
        APIResponseDto apiResponseDto= employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(apiResponseDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable(name="id") Long id){
        APIResponseDto apiResponseDto= employeeService.getEmployeeById(id);
        return ResponseEntity.ok(apiResponseDto);
    }






}


