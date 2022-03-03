package com.ibm.academia.movies.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="Actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long  actorId;

    @Column(name="name")
    private String name;


    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "films"})
    private Film film;
}
