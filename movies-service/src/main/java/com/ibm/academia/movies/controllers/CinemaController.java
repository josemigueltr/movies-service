package com.ibm.academia.movies.controllers;

import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.models.dtos.CinemaDto;
import com.ibm.academia.movies.models.entities.Offer;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.services.CinemaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cinema")
public class CinemaController {


    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    /**
     * Endpoint para dar de alta una oferta por parte de un usuario de tipo cinema
     * @param offer oferta que se creara
     * @param userCinema nombre de usuario del cinema que dara de alta la oferta
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Da de alta una oferta en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se creo la oferta"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
    }
    )
    @PreAuthorize("hasAuthority('ROL_CINEMA')")
    @PostMapping("/create")
    public ResponseEntity<?> createOffer(@RequestBody Offer offer, @RequestHeader("username") String userCinema) {
        try {
            cinemaService.createOffer(offer,userCinema);
            return new ResponseEntity<>("Oferta creada con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    /**
     * Endpoint para dar de baja una oferta por parte de un usuario de tipo cinema
     * @param userCinema nombre de usuario del cinema que dara de baja la oferta
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Da de baja una oferta en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se elimino la oferta"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
    }
    )
    @PreAuthorize("hasAuthority('ROL_CINEMA')")
    @PostMapping("/delete")
    public ResponseEntity<?> deleteOffer(@RequestParam Integer offerId, @RequestHeader("username") String userCinema) {
        try {
            cinemaService.deleteOffer(offerId.longValue(),userCinema);
            return new ResponseEntity<>("Oferta eliminada con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }

    /**
     * Endpoint para dar aplicar una oferta a un usuario
     * @param subs subscriptor alque se le aplicara la oferta
     * @param offerId oferta que se aplicara
     * @param userCinema nombre de usuario del cinema que dara de alta la oferta
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Aplica una oferta a un subscriptor", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se aplico la oferta"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
    }
    )
    @PreAuthorize("hasAuthority('ROL_CINEMA')")
    @PostMapping("/add")
    public ResponseEntity<?> addPoints( @RequestParam String subs, @RequestParam Integer offerId,@RequestHeader("username") String userCinema ) {
        try {
            cinemaService.applyOffer(offerId.longValue(),subs,userCinema);
            return new ResponseEntity<>("Se ha aplicado la oferta", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    /**
     * Endpoint que actualiza los datos de un usuario
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "Actualiza los datos de un cinema en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se actualizo  la informacion del cinema"),
            @ApiResponse(code = 403, message = "No esta autorizado para acceder al recurso"),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    }
    ) @PreAuthorize("hasAuthority('ROL_CINEMA')")
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestHeader("username") String username) {
        try {
            CinemaDto cinemaDto=cinemaService.updateCinema(user,username);
            return new ResponseEntity<>(cinemaDto, HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    @ApiOperation(value = "Da de baja a un usuario de tipo cinema en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se borro al usuario"),
            @ApiResponse(code = 400, message = "Los datos proporcionados son incorrectos"),
            @ApiResponse(code = 403, message = "No se esta autorizado para acceder al recursp"),
            @ApiResponse(code = 403, message = "El usuario no existe")
    }
    )
    @PreAuthorize("hasAuthority('ROL_CINEMA')")
    @PostMapping("/drop")
    public ResponseEntity<?> dropUser(@RequestHeader("username") String username) {
        try {
           cinemaService.deleteCinema(username);
            return new ResponseEntity<>("Usuario eliminado con exito", HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


}
