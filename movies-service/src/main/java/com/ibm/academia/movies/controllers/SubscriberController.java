package com.ibm.academia.movies.controllers;


import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.models.dtos.SubscriberDto;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.services.SubscriberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subs")
public class SubscriberController {

    private SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }


    @ApiOperation(value = "Da de baja a un usuario de tipo subscriptor en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se borro al usuario"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
            @ApiResponse(code = 403, message = "El usuario no existe")
    }
    )
    @PreAuthorize("hasAuthority('ROL_SUBSCRIBER')")
    @PostMapping("/drop")
    public ResponseEntity<?> dropUser(@RequestHeader("username") String username) {
        try {
            subscriberService.deleteSubs(username);
            return new ResponseEntity<>("Usuario eliminado con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    @ApiOperation(value = "Actualiza los datos de un subscriptor en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se actualizo  la informacion del subscriptor"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    ) @PreAuthorize("hasAuthority('ROL_SUBSCRIBER')")
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user,@RequestHeader("username") String username) {
        try {
            SubscriberDto subscriberDto=subscriberService.updateSubs(user,username);
            return new ResponseEntity<>(subscriberDto, HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    @PreAuthorize("hasAuthority('ROL_SUBSCRIBER')")
    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeCinema(@RequestParam String cinemaId,@RequestHeader("username") String username) {
        try {
            subscriberService.subscribeCinema(cinemaId,username);
            return new ResponseEntity<>("Subscripcion exitosa", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROL_SUBSCRIBER')")
    @PostMapping("/desubscribe")
    public ResponseEntity<?> desubscribeCinema(@RequestParam String cinemaId,@RequestHeader("username") String username) {
        try {
            subscriberService.desubscribeCinema(cinemaId,username);
            return new ResponseEntity<>("Desubscripcion exitosa", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

}
