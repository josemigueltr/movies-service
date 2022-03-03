package com.ibm.academia.movies.models.dtos;

import com.ibm.academia.movies.enums.RolType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriberDto extends UserDto{
    private  Long  points;

    public SubscriberDto(String username, String name, String email, RolType rol, Long points) {
        super(username, name, email, rol);
        this.points = points;
    }
}
