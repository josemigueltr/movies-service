package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.dtos.CinemaDto;
import com.ibm.academia.movies.models.entities.Offer;
import com.ibm.academia.movies.models.entities.User;

public interface CinemaService {
    public void createOffer(Offer offer,String cinemaUser);

    public void applyOffer(Long offerId, String username,String userCinema);

    public void deleteOffer(long longValue, String userCinema);

    public CinemaDto updateCinema(User user, String username);

    public void deleteCinema(String username);
}
