package com.ibm.academia.movies.mapper;

import com.ibm.academia.movies.models.dtos.FilmDto;
import com.ibm.academia.movies.models.entities.Film;

public class FilmMapper {
    public static FilmDto filmDtoMapper(Film film){
        return  FilmDto.builder().filmId(film.getFilmId())
                .date(film.getDate())
                .description(film.getDescription())
                .title(film.getTitle())
                .build();
    }
}
