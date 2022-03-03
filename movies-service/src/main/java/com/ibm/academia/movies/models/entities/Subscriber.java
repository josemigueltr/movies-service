package com.ibm.academia.movies.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.movies.enums.RolType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="Subscriber")
@PrimaryKeyJoinColumn(name = "subscriber_id")
public class Subscriber extends User{

    @Column(name= "points")
    private  Long  points;

    public Subscriber(String username, String password, String name, String email, Long points) {
        super(username, password, name, email);
        this.points = points;
        this.setRol(RolType.ROL_SUBSCRIBER);
    }


    @ManyToMany(mappedBy = "subscribers",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Cinema> cinemas = new HashSet<>();

    @Override
    public String toString() {
        return "Subscriber{" +
                "points=" + points +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return this.getUsername()==that.getUsername();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUsername());
    }
}
