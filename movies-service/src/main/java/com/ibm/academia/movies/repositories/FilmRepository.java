package com.ibm.academia.movies.repositories;

import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {

    @Query("select f from Film f where f.title like '%?1%'")
    public Optional<Film> findFilmByName(String name);


    @Query("Select f from Film f where f.filmId = ?1")
    public Optional<Film> getFilmById(Long id);


    @Query("SELECT f FROM Film f")
    public Iterable<Film> getAllFilms();

}
