package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.entities.Film;

import java.util.List;

public interface FilmService {
    public List<Film> getAllFilms();
    public Film getFilmByName(String name);
    public Film getFilmById(Long id);
}
