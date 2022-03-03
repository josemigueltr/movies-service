package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.dtos.SubscriberDto;
import com.ibm.academia.movies.models.entities.Subscriber;
import com.ibm.academia.movies.models.entities.User;

public interface SubscriberService {

   public void createSubs(Subscriber subscriber);

   public void deleteSubs(String username);

   public SubscriberDto updateSubs(User user, String username);

   public void subscribeCinema(String cinemaId, String username);

    public void desubscribeCinema(String cinemaId, String username);
}
