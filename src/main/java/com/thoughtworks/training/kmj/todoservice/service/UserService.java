package com.thoughtworks.training.kmj.todoservice.service;

import com.thoughtworks.training.kmj.todoservice.model.User;
import com.thoughtworks.training.kmj.todoservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String create(User user) {
        System.out.println("----222------");

        if(isRegister(user)){
            return "user has signed";
        }else{
            System.out.println("----333------");

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode("password");
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return "create success";
        }
    }

    private boolean isRegister(User user) {
        if(verify(user.getName(),user.getPassword())){
            return true;
        }
        return false;
    }


    public List<User> find() {
        return userRepository.findAll();
    }

    public boolean verify(String username, String password) {
        return userRepository.findOneByName(username)
                .map(User::getPassword)
                .filter(password::equals)
                .isPresent();
    }


    public int findIdByName(String username) {
        return userRepository.findIdByName(username).getId();
    }

    public String login(User user) {
        if(verify(user.getName(),user.getPassword())){
            return generateToken(user);
        }
        return "username or password error";
    }

    private String generateToken(User user) {

        int id = findIdByName(user.getName());
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("id",id);
        claims.put("name",user.getName());

        byte[] secretKey = "kmj".getBytes();

        //generate
        String token = Jwts.builder()
                .addClaims(claims)
//                .setExpiration(Date.from(Instant.now().minus(1,ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
        return token;
    }
}
