package com.ibm.academia.movies.models.dtos;

import com.ibm.academia.movies.enums.RolType;

public class CinemaDto extends UserDto{

    private  String web;

    private  String address;

    public CinemaDto(String username, String name, String email, RolType rol, String web, String address) {
        super(username, name, email, rol);
        this.web = web;
        this.address = address;
    }
}
