package com.ibm.academia.movies.services;

import com.ibm.academia.movies.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @Override
    public Film getFilmByName(String name) {
        Optional<Film> searchfilm=filmRepository.findFilmByName(name);
        if(!searchfilm.isPresent()){
            throw  new NotFoundException(HttpStatus.NOT_FOUND,String.format("La pelicula con el nombre %s no existe",name));
        }
        return searchfilm.get();
    }

    @Override
    public Film getFilmById(Long id) {
        Optional<Film> searchfilm=filmRepository.getFilmById(id);
        if(!searchfilm.isPresent()){
            throw  new NotFoundException(HttpStatus.NOT_FOUND,String.format("La pelicula con el id %d no existe",id));
        }
        return searchfilm.get();
    }


    @Override
    public List<Film> getAllFilms() {
        List<Film> films=(List<Film>)filmRepository.getAllFilms();
        return films;
    }
}
