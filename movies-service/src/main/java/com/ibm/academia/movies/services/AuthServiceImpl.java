package com.ibm.academia.movies.services;

import com.ibm.academia.movies.enums.RolType;
import com.ibm.academia.movies.exceptions.exeptiontypes.BadRequestException;
import com.ibm.academia.movies.models.dtos.UserLoginDto;
import com.ibm.academia.movies.models.dtos.UserLoginResponseDTO;
import com.ibm.academia.movies.models.entities.Admin;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Subscriber;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.repositories.UserRepository;
import com.ibm.academia.movies.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements  AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST,
                    String.format("El usuario con el username %s ya existe", user.getUsername()));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST,
                    String.format("El usuario con el correo %s ya existe", user.getEmail()));
        }
        if (user instanceof Cinema) {
            user.setRol(RolType.ROL_CINEMA);
        } else if (user instanceof Subscriber) {
            user.setRol(RolType.ROL_SUBSCRIBER);
            ((Subscriber) user).setPoints(0L);
        } else if (user instanceof Admin) {
            user.setRol(RolType.ROL_ADMIN);
        } else {
            user.setRol(RolType.ROL_USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public UserLoginResponseDTO accessUser(UserLoginDto login) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(login.getUsername(),
                                login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserLoginResponseDTO jwtGenerate = UserLoginResponseDTO.builder().token(jwt).username(userDetails.getUsername()).authorities(userDetails.getAuthorities()).build();
        return jwtGenerate;
    }

    @Override
    public void deleteUser(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        if (!user.isPresent()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST,
                    String.format("El usuario con el username %s ya existe", user.get().getUsername()));
        }
        try {
            userRepository.delete(user.get());
        }catch (Exception e){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


}