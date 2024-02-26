package com.mayu.departmentservice.controller;

import com.mayu.departmentservice.dto.DepartmentDto;
import com.mayu.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class departmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger logger = LoggerFactory.getLogger(departmentController.class);

    @GetMapping("/")
    public String emptyError(){
        return "Error";
    }
    @PostMapping("/addDepartment")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){



        DepartmentDto newdepartmentDto =  departmentService.saveDepartment(departmentDto);


       return new ResponseEntity<>(newdepartmentDto , HttpStatus.CREATED) ;

    }

    @GetMapping("/{getDepartment}")
    public ResponseEntity<DepartmentDto> getDepartmentData(@PathVariable("getDepartment") String code){
     //   Logger logger= (Logger) LoggerFactory.getLogger(departmentController.class);

        long start = System.currentTimeMillis();

        DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);

        long end = System.currentTimeMillis();

        long totalTime = end -start;
        logger.info("Total Time Taken " + totalTime +"millis");

        return ResponseEntity.ok(departmentDto);
    }


}
