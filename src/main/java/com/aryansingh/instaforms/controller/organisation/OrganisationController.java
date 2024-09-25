package com.aryansingh.instaforms.controller.organisation;

import com.aryansingh.instaforms.models.dtos.auth.LoginRequestDTO;
import com.aryansingh.instaforms.models.dtos.auth.TokenDTO;
import com.aryansingh.instaforms.models.dtos.org.OrganisationDTO;
import com.aryansingh.instaforms.service.userandorg.OrganisationService;
import com.aryansingh.instaforms.utils.AppConstants;
import com.aryansingh.instaforms.utils.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instaform/api/v1/organisation")
@AllArgsConstructor
@Slf4j
public class OrganisationController {

    OrganisationService organisationService;



    @PostMapping("/new")
    public ResponseEntity<ApiResponse<String>> createOrganisation(@Valid @RequestBody OrganisationDTO organisationDTO) {

        log.info("In /v1/organisation: "+organisationDTO.toString());

        organisationService.registerOrganisation(organisationDTO);

        return new ResponseEntity<>(new ApiResponse<>("organisation registered successfully", AppConstants.SUCCESS_MESSAGE),
                HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        TokenDTO tokenDTO = organisationService.login(loginRequestDTO);

        return new ResponseEntity<>(new ApiResponse<>(tokenDTO, AppConstants.SUCCESS_MESSAGE), HttpStatus.OK);
    }


    @GetMapping("/details")
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<ApiResponse<OrganisationDTO>> getorganisation() {

        OrganisationDTO organisationDTO = organisationService.getOrganisationDetails();
        return new ResponseEntity<>(new ApiResponse<>(organisationDTO, AppConstants.SUCCESS_MESSAGE), HttpStatus.OK);
    }



}

