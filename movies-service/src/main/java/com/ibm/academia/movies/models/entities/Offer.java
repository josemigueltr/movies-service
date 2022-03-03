package com.ibm.academia.movies.models.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="Offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long  offerId;

    @Column(name="description")
    private String description;


    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="deadline")
    private Date deadline;

    @Column(name="add_points")
    private Integer addPoints;

    @Column(name="sub_points")
    private Integer subPoints;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "players"})
    private Cinema cinema;

}
