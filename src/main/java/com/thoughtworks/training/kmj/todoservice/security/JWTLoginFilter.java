package com.thoughtworks.training.kmj.todoservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.training.kmj.todoservice.model.User;
import com.thoughtworks.training.kmj.todoservice.service.TokenAuthenticationService;
import com.thoughtworks.training.kmj.todoservice.utils.JSONResult;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {



    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }




    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        // JSON反序列化成
        User user = new ObjectMapper().readValue(req.getInputStream(), User.class);


        // 返回一个验证令牌
        if(!StringUtils.isEmpty(user.getName())){
            if(validateToken(user)){
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user.getName(),
                                user.getPassword())
                );

                return  SecurityContextHolder.getContext().getAuthentication();
            }
        }

        return null;


    }

    private boolean validateToken(User user) {
//        return userService.verify(user.getName(),user.getPassword());
        return true;
    }


    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JSONResult.fillResultString(500, "Internal Server Error!!!", JSONObject.NULL));
    }


}
