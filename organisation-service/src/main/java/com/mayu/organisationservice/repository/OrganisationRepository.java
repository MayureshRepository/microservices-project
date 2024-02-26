package com.mayu.organisationservice.repository;

import com.mayu.organisationservice.dto.OrganisationDto;
import com.mayu.organisationservice.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Long> {

    Organisation findByOrganisationCode(String orgCode);

}
