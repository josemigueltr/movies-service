package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.models.entities.User;

public interface AdminService {
    public void createCinema(Cinema cinema);

    public  void deleteAdmin(String username);

    public UserDto updateAdmin(User user, String username);

    public void createFilm(Film film, String username);

    public void removeFilm(long longValue, String username);
}
