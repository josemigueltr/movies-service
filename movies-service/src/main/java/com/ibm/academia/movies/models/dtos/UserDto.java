package com.ibm.academia.movies.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.academia.movies.enums.RolType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDto {

    @JsonProperty("nombre_de_usuario")
    private  String username;

    @JsonProperty("nombre")
    private  String name;

    @JsonProperty("correo")
    private  String email;

    @JsonProperty("rol")
    private RolType rol;

    public UserDto(String username, String name, String email, RolType rol) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.rol = rol;
    }
}
