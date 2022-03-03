package com.ibm.academia.movies.controllers;


import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.services.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    /**
     * Endpoint listar todas las peliculas disponibles en el sistema
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "obtiene las peliculas disponibles en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se regresa la lista de peliculas"),
            }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllMovies() {
        List<Film> films=filmService.getAllFilms();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }


    /**
     * Endpoint para buscar por nombre una una pelicula
     * @Param name nombre de la pelicula que se va a buscar
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "busca una pelicula por nombre en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se regresa la informacion de la pelicula"),
            @ApiResponse(code = 404, message = "La pelicula no existe"),
    }
    )
    @GetMapping("/search")
    public ResponseEntity<?> findFilm(@RequestParam String name) {
        try {
            Film film = filmService.getFilmByName(name);
            return new ResponseEntity<>(film, HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }


    /**
     * Endpoint para obtener informacion detallada de una pelicula
     * @Param id identificador de la pelicula que se va a buscar
     * @return Respuesta para confirmar que la solicitud fue exitosa
     */
    @ApiOperation(value = "obtiene las peliculas disponibles en el sistema", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Satisfactorio se regresa la informacion de la pelicula"),
            @ApiResponse(code = 404, message = "La pelicula no existe"),
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findFilmbyId(@PathVariable Integer id) {
        try {
            Film film = filmService.getFilmById(id.longValue());
            return new ResponseEntity<>(film, HttpStatus.OK);
        }catch (ExceptionType e){
            throw e;
        }
    }






}
