package com.ibm.academia.movies.controllers;

import com.ibm.academia.movies.models.dtos.OfferDto;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.services.FilmService;
import com.ibm.academia.movies.services.OfferService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService=offerService;
    }

    /**
     * Endpoint listar todas las ofertas disponibles en el sistema
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "obtiene las ofertas disponibles en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se regresa la lista de ofertas"),
    }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllOffers() {
        List<OfferDto> offers=offerService.getAll();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }


}
