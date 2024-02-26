package com.mayu.employeeservice.service.impl;

import com.mayu.employeeservice.dto.APIResponseDto;
import com.mayu.employeeservice.dto.DepartmentDto;
import com.mayu.employeeservice.dto.EmployeeDto;
import com.mayu.employeeservice.dto.OrganisationDto;
import com.mayu.employeeservice.entity.Employee;
import com.mayu.employeeservice.mapper.EmployeeMapper;
import com.mayu.employeeservice.repository.EmployeeRepository;
import com.mayu.employeeservice.service.ApiClient;
import com.mayu.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static Logger LOG= LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private ApiClient apiClient;
    @Override
    public EmployeeDto getEmployees(EmployeeDto employeeDto) {

//        Employee employee = new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail(),
//                employeeDto.getGetDepartmentCode());

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

//        EmployeeDto employeeDto1 = new EmployeeDto(
//                savedEmployee.getId(),
//                savedEmployee.getFirstName(),
//                savedEmployee.getLastName(),
//                savedEmployee.getEmail(),
//                savedEmployee.getGetDepartmentCode()
//        );

        EmployeeDto employeeDto1 = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return employeeDto1;
    }

    //@CircuitBreaker(name="${spring.application.name}" ,fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}" ,fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeByEmail(String email) {

        LOG.info("");
        Employee employee = employeeRepository.findByEmail(email);

//        ResponseEntity<DepartmentDto> responseEntity =restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getGetDepartmentCode() , DepartmentDto.class);
//
//        DepartmentDto departmentDto =responseEntity.getBody();

            DepartmentDto departmentDto = webClient.get()
               .uri("http://localhost:8080/api/departments/"+employee.getGetDepartmentCode() )
               .retrieve()
               .bodyToMono(DepartmentDto.class)
                    .block();


            EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getGetDepartmentCode(),
                employee.getOrganisationCode());

        APIResponseDto apiResponse = new APIResponseDto();
        apiResponse.setEmployeeDto(employeeDto);
        apiResponse.setDepartmentDto(departmentDto);



//        return employeeDto;
        return apiResponse ;
    }

    //FallBack Method is written here
    public APIResponseDto getDefaultDepartment(String email,Exception exception){


        Employee employee = employeeRepository.findByEmail(email);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D");
        departmentDto.setGetDepartmentCode("RD001");
        departmentDto.setGetDepartmentDescription("Research & Development");

        EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getGetDepartmentCode(),
        employee.getOrganisationCode());

        APIResponseDto apiResponse = new APIResponseDto();
        apiResponse.setEmployeeDto(employeeDto);
        apiResponse.setDepartmentDto(departmentDto);

        return apiResponse ;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();


//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/"+employee.getGetDepartmentCode() )
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        OrganisationDto organisationDto = webClient.get()
                .uri("http://localhost:8083/api/org/getByCode/"+ employee.getOrganisationCode())
                .retrieve()
                .bodyToMono(OrganisationDto.class)
                .block();

        DepartmentDto departmentDto = apiClient.getDepartmentData(employee.getGetDepartmentCode());





       EmployeeDto employeeDto = new EmployeeDto(
               employee.getId(),
               employee.getFirstName(),
               employee.getLastName(),
               employee.getEmail(),
               employee.getGetDepartmentCode(),
               employee.getOrganisationCode()
       );

       APIResponseDto apiResponseDto = new APIResponseDto();
       apiResponseDto.setEmployeeDto(employeeDto);
       apiResponseDto.setDepartmentDto(departmentDto);
       apiResponseDto.setOrganisationDto(organisationDto);


        return apiResponseDto;
    }
}
