package com.ibm.academia.movies.services;

import com.ibm.academia.movies.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.movies.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.movies.mapper.UserMapper;
import com.ibm.academia.movies.models.dtos.CinemaDto;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Offer;
import com.ibm.academia.movies.models.entities.Subscriber;
import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.repositories.CinemaRepository;
import com.ibm.academia.movies.repositories.OfferRepository;
import com.ibm.academia.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CinemaServiceImp implements CinemaService {

    private AuthService authService;
    private UserRepository userRepository;
    private OfferRepository offerRepository;

    @Autowired
    public CinemaServiceImp(AuthService authService, UserRepository userRepository, OfferRepository offerRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void createOffer(Offer offer,String username) {
        Optional<User> userCinema=userRepository.getUserByUsername(username);
        Cinema cine=(Cinema) userCinema.get();
        offer.setCinema(cine);
        cine.getOffers().add(offer);
        userRepository.save(cine);
    }

    @Override
    public void applyOffer(Long offerId, String username,String userCinema) {
        Optional<Offer> offer=offerRepository.getOfferById(offerId);
        if(!offer.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("La oferta con el id %d no existe",offerId));
        Optional<User> subscriber=userRepository.getUserByUsername(username);
        if(!subscriber.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El usuario con el username %s no existe",username));
        Optional<User> cinemaUsr=userRepository.getUserByUsername(userCinema);
        Cinema cinema= (Cinema) cinemaUsr.get();
        int index=((List<Subscriber>)cinema.getSubscribers()).indexOf(subscriber.get());
        if(index==-1)
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("El usuario con el username %s no se encuentra suscrito al cinema",userCinema));
        Subscriber subs=((List<Subscriber>)cinema.getSubscribers()).get(index);
        subs.setPoints(subs.getPoints()+offer.get().getAddPoints()-offer.get().getSubPoints());
        userRepository.save(subs);
    }

    @Override
    public void deleteOffer(long offerId, String userCinema) {
        Optional<Offer> offer=offerRepository.getOfferById(offerId);
        if(!offer.isPresent())
            throw new NotFoundException(HttpStatus.NOT_FOUND,String.format("La oferta con el id %d no existe",offerId));
        Optional<User> cinemaUsr=userRepository.getUserByUsername(userCinema);
        Cinema cinema=(Cinema) cinemaUsr.get();
        if(!cinema.getOffers().contains(offer))
            throw new NotFoundException(HttpStatus.BAD_REQUEST,String.format("La oferta con el id %d no esta asociada con el cinema",offerId));

        cinema.getOffers().remove(offer);
        userRepository.save(cinema);
    }

    @Override
    public CinemaDto updateCinema(User user, String username) {
        Cinema cine1=(Cinema) user;
        Optional<User> findUser=userRepository.getUserByUsername(username);
        Cinema cinema =(Cinema) findUser.get();

        cinema.setAddress(cine1.getAddress()!=null?cine1.getAddress(): cinema.getAddress());
        cinema.setName(cine1.getName()!=null?cine1.getName(): cinema.getName());
        cinema.setRol(cine1.getRol()!=null?cine1.getRol(): cinema.getRol());
        cinema.setEmail(cine1.getEmail()!=null?cine1.getEmail(): cinema.getEmail());
        cinema.setWeb(cine1.getWeb()!=null?cine1.getWeb(): cinema.getWeb());

        userRepository.save(cinema);

        return UserMapper.cinemaDtoMapper(cinema);
    }

    @Override
    public void deleteCinema(String username) {
            authService.deleteUser(username);
    }


}
