package com.ibm.academia.movies.mapper;

import com.ibm.academia.movies.models.dtos.OfferDto;
import com.ibm.academia.movies.models.entities.Offer;

public class OfferMapper {

    public static OfferDto mapOfferDTO (Offer offer){
        OfferDto offerDto=OfferDto.builder()
                .description(offer.getDescription())
                .deadline(offer.getDeadline())
                .addPoints(offer.getAddPoints())
                .subPoints(offer.getSubPoints()).build();
        return offerDto;
    }

}
