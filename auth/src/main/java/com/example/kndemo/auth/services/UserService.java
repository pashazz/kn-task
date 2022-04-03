package com.example.kndemo.auth.services;

import com.example.kndemo.auth.dto.CredentialsDto;
import com.example.kndemo.auth.dto.UserDto;
import com.example.kndemo.auth.exceptions.AppException;
import com.example.kndemo.auth.mappers.UserMapper;
import com.example.kndemo.auth.repository.UserRepository;
import com.example.kndemo.auth.yaml.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
         secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Public API

    public UserDto signIn(CredentialsDto credentialsDto) {
        var user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
             return userMapper.toUserDto(user, createToken(user));
         }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<User> userOpt = userRepository.findByLogin(login);

        if (userOpt.isEmpty()) {
            throw new AppException("User not found", HttpStatus.NOT_FOUND);
        }

        User user = userOpt.get();
        return userMapper.toUserDto(user, createToken(user));
    }



    private String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
