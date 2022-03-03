package com.ibm.academia.movies.services;

import com.ibm.academia.movies.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.movies.mapper.UserMapper;
import com.ibm.academia.movies.models.dtos.SubscriberDto;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Subscriber;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.repositories.CinemaRepository;
import com.ibm.academia.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriberServiceImp implements SubscriberService{

    private AuthService authService;

    CinemaRepository cinemaRepository;

    private UserRepository userRepository;

    @Autowired
    public SubscriberServiceImp(AuthService authService, CinemaRepository cinemaRepository, UserRepository userRepository) {
        this.authService = authService;
        this.cinemaRepository = cinemaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createSubs(Subscriber subscriber) {
        authService.createUser(subscriber);
    }


    @Override
    public void deleteSubs(String username) {
        authService.deleteUser(username);
    }

    @Override
    public SubscriberDto updateSubs(User user, String username) {
        Subscriber user1=(Subscriber)user;
        Optional<User> findUser=userRepository.getUserByUsername(username);
        if(!findUser.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El usuario con el username %s no existe",username));
        Subscriber sub =(Subscriber) findUser.get();

        sub.setPoints(user1.getPoints()!=null?user1.getPoints(): sub.getPoints());
        sub.setEmail(user1.getEmail()!=null?user1.getEmail(): sub.getEmail());
        sub.setName(user1.getName()!=null?user1.getName(): sub.getName());
        sub.setRol(user1.getRol()!=null?user1.getRol(): sub.getRol());
        userRepository.save(sub);

        return UserMapper.subsDtoMapper(sub);

    }

    @Override
    public void subscribeCinema(String cinemaId, String username) {
        Optional<User> findUser=userRepository.getUserByUsername(username);
        Optional<User> findCinema=userRepository.getUserByUsername(cinemaId);
        if(!findCinema.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El cinema %s no existe",cinemaId));
        Cinema cine=(Cinema) findCinema.get();
        cine.getSubscribers().add((Subscriber) findUser.get());
        ((Subscriber) findUser.get()).getCinemas().add(cine);
        userRepository.save(cine);
    }

    @Override
    public void desubscribeCinema(String cinemaId, String username) {
        Optional<User> findUser=userRepository.getUserByUsername(username);
        Optional<User> findCinema=userRepository.getUserByUsername(cinemaId);
        if(!findCinema.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El cinema %s no existe",cinemaId));
        Cinema cine=(Cinema) findCinema.get();
        if (!cine.getSubscribers().contains((Subscriber)findUser.get()))
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El cinema %s no tiene como subscriptor al usuario %d",cinemaId,username));
            cine.getSubscribers().remove((Subscriber) findUser.get());

        userRepository.save(cine);
    }
}
