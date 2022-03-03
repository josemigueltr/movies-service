package com.ibm.academia.movies.mapper;

import com.ibm.academia.movies.models.dtos.CinemaDto;
import com.ibm.academia.movies.models.dtos.OfferDto;
import com.ibm.academia.movies.models.dtos.SubscriberDto;
import com.ibm.academia.movies.models.dtos.UserDto;
import com.ibm.academia.movies.models.entities.Cinema;
import com.ibm.academia.movies.models.entities.Offer;
import com.ibm.academia.movies.models.entities.Subscriber;
import com.ibm.academia.movies.models.entities.User;

public class UserMapper {

    public static UserDto userDtoMapper (User user){
        UserDto userDto= new UserDto(user.getUsername(), user.getName(), user.getEmail(), user.getRol());
        return  userDto;
    }

    public static SubscriberDto subsDtoMapper (Subscriber user){
        SubscriberDto subscriberDto= new SubscriberDto(user.getUsername(), user.getName(), user.getEmail(),user.getRol(), user.getPoints());
        return  subscriberDto;
    }

    public static CinemaDto cinemaDtoMapper (Cinema user){
        CinemaDto cinemaDto=new CinemaDto(user.getUsername(), user.getName(), user.getEmail(), user.getRol(), user.getWeb(), user.getAddress());
        return cinemaDto;
    }
}
