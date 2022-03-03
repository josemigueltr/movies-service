package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.dtos.FilmDto;
import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.User;

import java.util.List;

public interface UserService {
    public UserDto updateUser(User user,String username);

    public void deleteUser(String username);

    public void likeMovie(Integer filmId, String username);

    public List<FilmDto> getFavoriteFilm(String username);
}
