package com.ibm.academia.movies.repositories;

import com.ibm.academia.movies.models.entities.Film;
import com.ibm.academia.movies.models.entities.Offer;
import com.ibm.academia.movies.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long> {
    @Query("SELECT o FROM Offer o")
    public Iterable<Offer> getAllOffers();

    @Query("Select o from Offer o where o.offerId= ?1")
    public Optional<Offer> getOfferById(Long id);
}
