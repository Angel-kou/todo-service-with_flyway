package com.thoughtworks.training.kmj.todoservice.security;

import com.google.common.collect.ImmutableList;
import com.google.common.net.HttpHeaders;
import com.thoughtworks.training.kmj.todoservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class TodoAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getContextPath().startsWith("/login");
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("------token-----------"+token);
        if(!StringUtils.isEmpty(token)){
            int id = validateToken(token);
            if( id != 0){
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(id,null,
                                ImmutableList.of(new SimpleGrantedAuthority("dev"),
                                        new SimpleGrantedAuthority("play")))
                );
                Object y =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                System.out.println("xyz-------------"+y);
            }
        }
        filterChain.doFilter(request,response);

    }

//    private boolean validateToken(String token) {
//        String[] userPass = token.split(":");
//        String username = userPass[0];
//        String password = userPass[1];
//        return userService.verify(username,password);
//    }

    private int validateToken(String token) {

        byte[] secretKey = "kmj".getBytes();
        Claims body = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return (int) body.get("id");
    }
}
