package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.dtos.UserLoginDto;
import com.ibm.academia.movies.models.dtos.UserLoginResponseDTO;
import com.ibm.academia.movies.models.entities.User;

public interface AuthService {

    public  void createUser(User user);

    public UserLoginResponseDTO accessUser(UserLoginDto login);

    public  void deleteUser(String username);

}
