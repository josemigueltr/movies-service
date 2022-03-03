package com.ibm.academia.movies.services;

import com.ibm.academia.movies.models.dtos.OfferDto;

import java.util.List;

public interface OfferService {
    public List<OfferDto> getAll();
}
