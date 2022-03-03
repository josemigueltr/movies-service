package com.ibm.academia.movies.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@Builder
public class OfferDto {

    @JsonProperty("descripcion")
    private String description;

    @Column(name="fecha_vencimineto")
    private Date deadline;

    @Column(name="punto_a_favor")
    private Integer addPoints;

    @Column(name="puntos_en_contra")
    private Integer subPoints;

}
