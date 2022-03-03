package com.ibm.academia.movies.security.filters;

import com.ibm.academia.movies.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.movies.security.UserDetailServiceImpl;
import com.ibm.academia.movies.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter  extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailServiceImpl userDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        HeaderParams requestWrapper = new HeaderParams(request);
        try{
            String token = getToken(request);
            if(token != null && jwtProvider.validateToken(token)){
                String nombreUsuario = jwtProvider.getUsernameToken(token);
                ///
                requestWrapper.addHeader("username", nombreUsuario);
                ///
                UserDetails userDetails = userDetailService.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            throw new NotFoundException(HttpStatus.NOT_FOUND,"Credenciales incorrectas");
        }

        filterChain.doFilter(requestWrapper, response);
    }


    //Obtenemos el token sin Bearer + el espacio
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;

    }
}
