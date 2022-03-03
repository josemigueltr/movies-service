package com.ibm.academia.movies.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="Film")
public class Film  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "film_id")
        private Long filmId;

        @Column(name = "title")
        private  String  title;

        @Column(name = "description")
        private  String  description;

        @Column(name = "date_created")
        @JsonFormat(pattern="yyyy-MM-dd")
        @CreationTimestamp
        private Date date;

        @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "film"})
        Set<Category> categories=new HashSet<>();


        @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "film"})
        Set<Actor> actors=new HashSet<>();

        @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "film"})
        Set<Director> directors=new HashSet<>();

        @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
        @JoinColumn(name = "username")
        @JsonIgnoreProperties({"hibernateLazyInitializer", "films"})
        private Admin user;


        @ManyToMany(mappedBy = "films",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
        @JsonIgnore
        private Set<User> users = new HashSet<>();


        @PrePersist
        private  void preCreated(){
                this.date=new Date();
        }


}
