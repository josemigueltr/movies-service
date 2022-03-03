package com.ibm.academia.movies.controllers;


import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    /**
     * Endpoint para dar de alta un cinema
     *@param cinema cinema que se dara de alta
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Da de alta a un usuario de tipo cinema en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se creo la cuenta"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp")
    }
    )

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createCinema(@RequestBody Cinema cinema) {
        try {
            adminService.createCinema(cinema);
            return new ResponseEntity<>("Cinema creado con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

    /**
     * Endpoint para eliminar la cuenta de un usuario de tipo administrador
     *@param username username del usuario que se dara de baja del sistema
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Da de baja a un usuario de tipo administrador en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se borro al usuario"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
            @ApiResponse(code = 403, message = "El usuario no existe")
    }
    )
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/drop")
    public ResponseEntity<?> dropUser(@RequestHeader("username") String username) {
        try {
            adminService.deleteAdmin(username);
            return new ResponseEntity<>("Usuario eliminado con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    /**
     * Endpoint que actualiza los datos de un usuario
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Actualiza los datos de un admin en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se actualizo  la informacion del admin"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    ) @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestHeader("username") String username) {
        try {
            UserDto userDto=adminService.updateAdmin(user,username);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }



    /**
     * Endpoint para dar de alta una pelicula
     *@param film peliucla que se dara de alta
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Da de alta una pelicula en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se creo la pelicula"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    ) @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/movie")
    public ResponseEntity<?> createFilm(@RequestBody Film film, @RequestHeader("username") String username) {
        try {
            adminService.createFilm(film,username);
            return new ResponseEntity<>("Pelicula creada con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

    /**
     * Endpoint para dar de baja una pelicula
     *@param filmId pelicula que se dara de baja
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Da de baja una pelicula en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se dio de baja la pelicula"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "La pelicula no existe"),
    }
    ) @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/remove")
    public ResponseEntity<?> removeFilm(@RequestBody Integer filmId, @RequestHeader("username") String username) {
        try {
            adminService.removeFilm(filmId.longValue(),username);
            return new ResponseEntity<>("Se  ha eliminado la pelicula", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }



}
