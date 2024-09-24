package com.aryansingh.instaforms.controller.user;

import com.aryansingh.instaforms.models.dtos.auth.LoginRequestDTO;
import com.aryansingh.instaforms.models.dtos.auth.TokenDTO;
import com.aryansingh.instaforms.models.dtos.user.UserDTO;
import com.aryansingh.instaforms.service.userandorg.UserService;
import com.aryansingh.instaforms.utils.AppConstants;
import com.aryansingh.instaforms.utils.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instaform/api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    UserService userService;



    @PostMapping("/new")
    public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody UserDTO userDTO) {

        log.info("In /v1/user: "+userDTO.toString());

         userService.registerUser(userDTO);

            return new ResponseEntity<>(new ApiResponse<>("User registered successfully", AppConstants.SUCCESS_MESSAGE),
                    HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        TokenDTO tokenDTO = userService.login(loginRequestDTO);

        return new ResponseEntity<>(new ApiResponse<>(tokenDTO, AppConstants.SUCCESS_MESSAGE), HttpStatus.OK);
    }


    @GetMapping("/userDetails")
    @PreAuthorize("hasRole('ROLE_SINGLE_USER')")
    public ResponseEntity<ApiResponse<UserDTO>> getUser() {

        UserDTO userDTO = userService.getUserDetails();
        return new ResponseEntity<>(new ApiResponse<>(userDTO, AppConstants.SUCCESS_MESSAGE), HttpStatus.OK);
    }



}
