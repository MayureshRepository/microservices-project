package com.mayu.organisationservice.service;


import com.mayu.organisationservice.dto.OrganisationDto;

public interface OrganisationService {

    public OrganisationDto addOrgansation(OrganisationDto organisationDto);


    public OrganisationDto findByOrgCode(String orgCode);




}
