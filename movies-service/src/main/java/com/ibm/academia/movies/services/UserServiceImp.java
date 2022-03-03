package com.ibm.academia.movies.services;


import com.ibm.academia.movies.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.movies.mapper.FilmMapper;
import com.ibm.academia.movies.mapper.UserMapper;
import com.ibm.academia.movies.models.dtos.FilmDto;
import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.repositories.FilmRepository;
import com.ibm.academia.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImp implements UserService   {

    private AuthService authService;
    private UserRepository userRepository;
    private FilmRepository filmRepository;

    @Autowired
    public UserServiceImp(AuthService authService, UserRepository userRepository, FilmRepository filmRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public UserDto updateUser(User user,String username) {
        Optional<User> findUser=userRepository.getUserByUsername(username);
        if(!findUser.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El usuario con el username %s no existe",username));
        User user1=findUser.get();
        user1.setEmail((user.getEmail()!=null)?user.getEmail():user1.getEmail());
        user1.setName((user.getName()!=null)?user.getName():user1.getName());
        user1.setRol((user.getRol()!=null)?user.getRol():user1.getRol());
        userRepository.save(user1);
        return UserMapper.userDtoMapper(user1);
    }

    @Override
    public void deleteUser(String username) {
        authService.deleteUser(username);
    }

    @Override
    public void likeMovie(Integer filmId, String username) {
        Optional<User> findUser=userRepository.getUserByUsername(username);
        Optional<Film> film=filmRepository.getFilmById(filmId.longValue());
        if(!film.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("La pelicula no existe"));
        findUser.get().getFilms().add(film.get());
        userRepository.save(findUser.get());
    }


    @Override
    public List<FilmDto> getFavoriteFilm(String username) {
        Optional<User> findUser=userRepository.getUserByUsername(username);
        List<FilmDto> films=findUser.get().getFilms().stream().map(f -> FilmMapper.filmDtoMapper(f)).collect(Collectors.toList());
        return films;
    }
}
