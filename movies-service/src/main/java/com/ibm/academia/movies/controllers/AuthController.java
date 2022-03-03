package com.ibm.academia.movies.controllers;

import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.models.dtos.UserLoginDto;
import com.ibm.academia.movies.models.dtos.UserLoginResponseDTO;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.services.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    /**
     * Endpoint para registrar un usuario en el sistema
     *@param user usuario que se registrara en el sistema
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Registra aun usuario en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio el usuario se registro correctamente"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
    }
    )
    @PostMapping("/singup")
    public ResponseEntity<?> singup(@RequestBody User user) {
        try{
            authService.createUser(user);
            return new ResponseEntity<>("Registro Exitoso", HttpStatus.OK);
        }catch (ExceptionType exception){
            throw exception;
        }
    }

    /**
     * Endpoint para hacer login con una cuenta de usuario
     *@param login objeto que contiene la informacuon necesaria para iniciar sesion
     * @return Respuesta con las credenciales necesarias para inicial sesion
     */
    @ApiOperation(value = "Inicia sesion con la cuenta de un usuario en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se inicio sesion"),
            @ApiResponse(code = 403, message = "Credenciales incorrectas"),
            @ApiResponse(code = 404, message = "El usuario no existe")
    }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto login) {
        try{
            UserLoginResponseDTO response = authService.accessUser(login);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ExceptionType exception){
            throw exception;
        }
    }


}
