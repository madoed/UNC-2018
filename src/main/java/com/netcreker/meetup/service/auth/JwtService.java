package com.netcreker.meetup.service.auth;

import com.netcreker.meetup.service.user.UserService;
import com.netcreker.meetup.entity.user.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtService {
    @Autowired
    private UserService userService;

    public String getToken(String username, String password) throws Exception {
        if (username == null || password == null)
            return null;
        User user = userService.loadByUsername(username);
        Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(user.getPassword())) {
            tokenData.put("clientType", "user");
            tokenData.put("userID", user.getId());
            tokenData.put("username", user.getUsername());
            tokenData.put("token_creation_date", new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            tokenData.put("token_expiration_date", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            String token = jwtBuilder.signWith(key).compact();
            return token;
        } else {
            throw new Exception("Authentication error");
        }
    }

}
