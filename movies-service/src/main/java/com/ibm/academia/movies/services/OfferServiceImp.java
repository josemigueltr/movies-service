package com.ibm.academia.movies.services;

import com.ibm.academia.movies.mapper.OfferMapper;
import com.ibm.academia.movies.models.dtos.OfferDto;
import com.ibm.academia.movies.models.entities.Offer;
import com.ibm.academia.movies.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImp implements OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImp(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Override
    public List<OfferDto> getAll() {
        List<Offer> offers=(List<Offer>)offerRepository.getAllOffers();
        List<OfferDto> offerDtos=offers.stream().map(o -> OfferMapper.mapOfferDTO(o))
                .collect(Collectors.toList());
        return offerDtos;
    }
}
