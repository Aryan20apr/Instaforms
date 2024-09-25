package com.aryansingh.instaforms.service.userandorg.impl;

import com.aryansingh.instaforms.models.dtos.auth.LoginRequestDTO;
import com.aryansingh.instaforms.models.dtos.auth.TokenDTO;
import com.aryansingh.instaforms.models.dtos.user.UserDTO;
import com.aryansingh.instaforms.models.entities.userAndAuth.Role;
import com.aryansingh.instaforms.models.entities.userAndAuth.SingleUser;
import com.aryansingh.instaforms.repositories.organduser.UserRepository;
import com.aryansingh.instaforms.service.role.RoleService;
import com.aryansingh.instaforms.service.userandorg.UserService;
import com.aryansingh.instaforms.utils.AppConstants;
import com.aryansingh.instaforms.utils.exceptions.ApiException;
import com.aryansingh.instaforms.utils.security.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleService roleService;
    private ObjectMapper objectMapper;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean registerUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String username = userDTO.getUserName();

       Optional<SingleUser> user =  userRepository.findByUserNameOrEmail(email,username);

       if(user.isEmpty()){
           SingleUser newUser= SingleUser.builder().
           userName(userDTO.getUserName())
                   .firstName(userDTO.getFirstName())
                   .lastName(userDTO.getLastName())
                   .email(userDTO.getEmail())
                   .phoneNumber(userDTO.getPhoneNumber())
                   .profileImage(userDTO.getProfileImage())
                   .build();

           // TODO: Add phone number and email verification


         Set<Role> roles = new HashSet<>();

         userDTO.getRoles().forEach(roleName->{
             Role role =roleService.findByRoleName(roleName);
             roles.add(role);
         });
         newUser.setRoles(roles);

         newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

         userRepository.save(newUser);
       } else{
           throw new ApiException("User with the email or username already exists");
       }
       return true;
    }

    @Override
    @Transactional
    public UserDTO getUserDetails() {
       String userToken = JWTUtils.extract(AppConstants.UNIQUE_TOKEN);
        log.info("Obtained user token: "+userToken);
       Optional<SingleUser> user = userRepository.findByUserToken(userToken);

       if(user.isEmpty()){
           throw new ApiException("User not found");
       } else {
           UserDTO userDTO = UserDTO.builder()
                   .userName(user.get().getUserName())
                   .firstName(user.get().getFirstName())
                   .lastName(user.get().getLastName())
                   .email(user.get().getEmail())
                   .phoneNumber(user.get().getPhoneNumber())
                   .profileImage(user.get().getProfileImage())
                   .build();

           Set<Role> eroles= user.get().getRoles();
           Set<String> roles = new HashSet<>();
           eroles.forEach(role->{roles.add(role.getRoleName());});
           userDTO.setRoles(roles);
           return
                    userDTO;
       }
    }


    @Override
    public TokenDTO login(LoginRequestDTO loginRequestDTO) {
            log.info("Login request : {}", loginRequestDTO);
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<SingleUser> singleUser = userRepository.findByUserNameOrEmail(userName,userName);
            if(singleUser.isEmpty()){
                throw new ApiException("User not found with provided username or email.");
            }
           String jwt =  JWTUtils.generateToken(singleUser.get().getUserName(),AppConstants.ENTITY_TYPE_USER ,singleUser.get().getUserToken());
            return TokenDTO.builder().token(jwt).build();

            // TODO: Add refresh token also
        } else{
            throw new ApiException("User not authenticated");
        }
    }
}
