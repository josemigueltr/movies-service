package com.ibm.academia.movies.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Builder
public class FilmDto {

    @JsonProperty("identificador")
    private Long filmId;

    @JsonProperty("titulo")
    private  String  title;

    @JsonProperty("descripcion")
    private  String  description;

    @JsonProperty("fecha de creacion")
    @CreationTimestamp
    private Date date;

}
