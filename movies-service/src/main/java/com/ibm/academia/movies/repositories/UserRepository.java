package com.ibm.academia.movies.repositories;

import com.ibm.academia.movies.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query("Select u from User u where u.username = ?1")
    public Optional<User> getUserByUsername(String username);

    public boolean existsByUsername (String username);

    public boolean existsByEmail(String email);
}
