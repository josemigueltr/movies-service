package com.ibm.academia.movies.services;

import com.ibm.academia.movies.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.Admin;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.repositories.FilmRepository;
import com.ibm.academia.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class adminServiceImp implements  AdminService{

    private AuthService authService;

    private UserService userService;

    private UserRepository userRepository;

    private FilmRepository filmRepository;

    @Autowired
    public adminServiceImp(AuthService authService, UserService userService, UserRepository userRepository, FilmRepository filmRepository) {
        this.authService = authService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public void createCinema(Cinema cinema) {
        authService.createUser(cinema);
    }

    @Override
    public void deleteAdmin(String username) {
        authService.deleteUser(username);
    }

    @Override
    public UserDto updateAdmin(User user, String username) {
       return userService.updateUser(user,username);
    }

    @Override
    public void createFilm(Film film, String username) {
        Optional<User> findUser = userRepository.getUserByUsername(username);
        Admin admin=(Admin)  findUser.get();
        film.setUser(admin);
        admin.getFilms().add(film);
        userRepository.save(admin);
    }

    @Override
    public void removeFilm(long filmId, String username) {
        Optional<User> findUser = userRepository.getUserByUsername(username);
        Optional<Film> film = filmRepository.getFilmById(filmId);
        Admin admin=(Admin)  findUser.get();
        if(!admin.getFilms().contains(film.get()))
            throw new NotFoundException(HttpStatus.BAD_REQUEST,String.format("La pelicula con el id %d no esta asociada con el administrador",filmId));
        admin.getFilms().remove(film.get());
        userRepository.save(admin);
    }
}
