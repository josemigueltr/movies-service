package com.ibm.academia.movies.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ibm.academia.movies.enums.RolType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="User")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use=JsonTypeInfo.Id.NAME,
        include =JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class,name="user"),
        @JsonSubTypes.Type(value = Admin.class,name="admin"),
        @JsonSubTypes.Type(value = Cinema.class,name="cinema"),
        @JsonSubTypes.Type(value = Subscriber.class,name="subscriber")
})
public class User {

    @Id
    @Column(name= "Username")
    private  String username;

    @Column(name= "password")
    private  String password;

    @Column(name= "name")
    private  String name;

    @Column(name= "email")
    private  String email;

    @Column(name= "rol")
    @Enumerated(EnumType.STRING)
    private RolType rol;

    /**
     * Contructor para clases hijas que proporcionan su rol
     */

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.rol = RolType.ROL_USER;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setRol(RolType rol) {
        this.rol = rol;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "User_films",
            joinColumns = {@JoinColumn(name = "username")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")}
    )
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "players"})
    @JsonIgnore
    private Set<Film> films = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}

