package com.aryansingh.instaforms.service.userandorg;

import com.aryansingh.instaforms.models.dtos.auth.LoginRequestDTO;
import com.aryansingh.instaforms.models.dtos.auth.TokenDTO;
import com.aryansingh.instaforms.models.dtos.org.OrganisationDTO;

public interface OrganisationService {

    boolean registerOrganisation(OrganisationDTO organisationDTO);

    OrganisationDTO getOrganisationDetails();

    TokenDTO login(LoginRequestDTO loginRequestDTO);

}
