package com.aryansingh.instaforms.service.userandorg;

import com.aryansingh.instaforms.models.dtos.auth.LoginRequestDTO;
import com.aryansingh.instaforms.models.dtos.auth.TokenDTO;
import com.aryansingh.instaforms.models.dtos.user.UserDTO;

public interface UserService {


     boolean registerUser(UserDTO userDTO);

     UserDTO getUserDetails();

    TokenDTO login(LoginRequestDTO loginRequestDTO);

}
