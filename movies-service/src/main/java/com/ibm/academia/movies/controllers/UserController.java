package com.ibm.academia.movies.controllers;

import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.models.dtos.FilmDto;
import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Endpoint que actualiza los datos de un usuario
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Actualiza los datos de un usuario en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se actualizo  la informacion del usuario"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    )
    @PreAuthorize("hasAuthority('ROL_USER')")
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user,@RequestHeader("username") String username) {
        try {
             UserDto userRegister = userService.updateUser(user,username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }



    @ApiOperation(value = "Da de baja a un usuario del sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se borro al usuario"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
            @ApiResponse(code = 403, message = "El usuario no existe")
    }
    )
    @PreAuthorize("hasAuthority('ROL_USER')")
    @PostMapping("/drop")
    public ResponseEntity<?> dropUser(@RequestHeader("username") String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>("Usuario eliminado con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

    /**
     * Endpoint que registra el seguimiento de un usuario a una pelicula
     * @param filmId identificador de la pelicula
     * @param username  username del usuario
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "registra el seguimiento de un usuario a una pelicula", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se registro el seguimiento"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    )
    @PreAuthorize("hasAuthority('ROL_USER')")
    @PostMapping("/like")
    public ResponseEntity<?> likeFilm(@RequestParam Integer filmId,@RequestHeader("username") String username) {
        try {
            userService.likeMovie(filmId,username);
            return new ResponseEntity<>("Se ha registrado el me gusta", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

    /**
     * Endpoint obtiene la lista de peliculas que ha seguido el usuario
     * @param username  username del usuario
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Obtiee la lista d peliculas que le han gustado al usuario", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se regresa la lista de peliculas"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    )
    @PreAuthorize("hasAuthority('ROL_USER')")
    @GetMapping("/favorite")
    public ResponseEntity<?> getFilms(@RequestHeader("username") String username) {
        try {
            List<FilmDto> films=userService.getFavoriteFilm(username);
            return new ResponseEntity<>("Se ha registrado el me gusta", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }
}
