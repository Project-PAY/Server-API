package com.pay.domain.service;

import com.pay.domain.model.User;
import com.pay.domain.repository.AuthRepository;
import com.pay.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements AuthRepository {

    @Value("${pay.jwt.header}")
    private String HEADER;
    @Value("${pay.jwt.salt}")
    private String SALT;

    @Autowired
    private UserRepository userRepository;

    private final String CLAIM = "USER";


    public String auth(@Valid String identify,
                       @Valid String password) {
        User user = userRepository.findByIdentifyAndPasswordAndAvailable(identify, password, "able");

        if (user == null)
            return null;
        else
            return createUser(user.getIndex(), user.getIdentify(), user.getName());
    }


    public boolean isUser(@Valid String token) {
        if (token == null)
            return false;

        User user = getUser(token);

        if (user == null)
            return false;

        return userRepository.getOne(getUser(token).getIndex()) != null;
    }


    public String createUser(@Valid Long index,
                             @Valid String identify,
                             @Valid String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("index", index);
        map.put("identify", identify);
        map.put("name", name);

        String token = doCreate(CLAIM, map);
        return token;
    }


    public void destroyUser() {
        doDestroy(CLAIM);
    }


    public User getUser(@Valid String token) {
        Map map = doGet(CLAIM, token);

        if (map == null)
            return null;

        return User.builder()
                .identify(String.valueOf(map.get("identify")))
                .name(String.valueOf(map.get("name")))
                .index(Long.parseLong(String.valueOf(map.get("index"))))
                .build();
    }

    public Long getUserIndex(@Valid String token) {
        Map map = doGet(CLAIM, token);
        return Long.parseLong(String.valueOf(map.get("index")));
    }

    @Override
    public String doCreate(String claim, Map map) {
        String token = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("at", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .setSubject(claim)
                .claim(claim, map)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();

        return token;
    }

    @Override
    public void doDestroy(String claim) {
    }


    @Override
    public Map<String, Object> doGet(String claim, String token) {
        Jws<Claims> claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);
        } catch (Exception exception) {
            return null;
        }

        return (Map<String, Object>) claims.getBody().get(claim);
    }


    @Override
    public byte[] generateKey() {
        return java.util.Base64.getEncoder().encode(SALT.getBytes());
    }

}
