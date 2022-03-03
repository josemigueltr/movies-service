package com.ibm.academia.movies.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
public class UserLoginResponseDTO {

    @JsonProperty("token_de_acceso")
    private String token;

    @JsonProperty("nombre_usuario")
    private String username;

    @JsonProperty("rol")
    private Collection<? extends GrantedAuthority> authorities;

}
