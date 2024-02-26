package com.mayu.organisationservice.service.impl;

import com.mayu.organisationservice.dto.OrganisationDto;
import com.mayu.organisationservice.entity.Organisation;
import com.mayu.organisationservice.mapper.OrganisationMapper;
import com.mayu.organisationservice.repository.OrganisationRepository;
import com.mayu.organisationservice.service.OrganisationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {


    @Autowired
    private OrganisationRepository organisationRepository;

    public OrganisationDto addOrgansation(OrganisationDto organisationDto){
        Organisation organisation = OrganisationMapper.mapToOrganisation(organisationDto);
        Organisation savedOrg = organisationRepository.save(organisation);
        OrganisationDto organisationDto1 = OrganisationMapper.mapToOrganistionDto(savedOrg);
        return organisationDto1;

    }

    @Override
    public OrganisationDto findByOrgCode(String orgCode) {

        Organisation organisation = organisationRepository.findByOrganisationCode(orgCode);

        OrganisationDto organisationDto = OrganisationMapper.mapToOrganistionDto(organisation);

        return organisationDto;
    }


}
