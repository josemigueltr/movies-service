package com.ibm.academia.movies.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="Cinema")
@PrimaryKeyJoinColumn(name = "cinema_id")
public class Cinema extends User {

        @Column(name="web")
        private  String web;

        @Column(name="address")
        private  String address;

        public Cinema(String username, String password, String name, String email,  String web, String address) {
                super(username, password, name, email);
                this.web = web;
                this.setRol(RolType.ROL_CINEMA);
                this.address = address;
        }





        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
        @JoinTable(
                name = "Cinema_subscriber",
                joinColumns = {@JoinColumn(name = "cinema_id")},
                inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
        )
        //@JsonIgnoreProperties({"hibernateLazyInitializer", "players"})
        @JsonIgnore
        private Set<Subscriber> subscribers = new HashSet<>();


        @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
        @JsonIgnoreProperties({"cinema"})
        private Set<Offer> offers= new HashSet<>();


        @Override
        public String toString() {
                return "Cinema{" +
                        "web='" + web + '\'' +
                        ", address='" + address + '\'' +
                        "} " + super.toString();
        }
}
