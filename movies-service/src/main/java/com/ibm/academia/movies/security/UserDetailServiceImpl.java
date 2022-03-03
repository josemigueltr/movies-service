package com.ibm.academia.movies.security;

import com.ibm.academia.movies.models.entities.User;
import com.ibm.academia.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.getUserByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException("Usuario no encontrado");
        System.out.println("\n\n ENCONTRE AL USUARIO");
        return new UserDetailsImp(user.get());
    }
}
