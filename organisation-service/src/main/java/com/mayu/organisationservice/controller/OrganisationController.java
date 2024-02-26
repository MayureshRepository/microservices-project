package com.mayu.organisationservice.controller;


import com.mayu.organisationservice.dto.OrganisationDto;
import com.mayu.organisationservice.service.OrganisationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/org/")
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    private final static Logger Log = LoggerFactory.getLogger(OrganisationController.class) ;

    @PostMapping("addOrg")
    public ResponseEntity<OrganisationDto> addOrg(@RequestBody OrganisationDto organisationDto){
        Log.info("Method started in Org Controller start "+ new Date());
        OrganisationDto organisationDto1 = organisationService.addOrgansation(organisationDto);

        Log.info("Method started in Org Controller end "+ new Date());
        return new ResponseEntity<>(organisationDto1 , HttpStatus.CREATED);
    }

    @GetMapping("getByCode/{orgCode}")
    public ResponseEntity<OrganisationDto> findByOrgCode(@PathVariable (name="orgCode") String orgCode){
        OrganisationDto organisationDto = organisationService.findByOrgCode(orgCode);

        return ResponseEntity.ok(organisationDto);

    }






}
