package com.ibm.academia.movies.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.movies.enums.RolType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="Admin")
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends  User{

    public Admin(String username, String password, String name, String email) {
        super(username, password, name, email);
        this.setRol(RolType.ROL_ADMIN);
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties({"user"})
    private Set<Film> films = new HashSet<>();
}
