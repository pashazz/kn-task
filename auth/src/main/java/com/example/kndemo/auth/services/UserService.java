package com.example.kndemo.auth.services;

import com.example.kndemo.auth.config.AuthConfig;
import com.example.kndemo.auth.dto.CredentialsDto;
import com.example.kndemo.auth.dto.UserDto;
import com.example.kndemo.auth.exceptions.AppException;
import com.example.kndemo.auth.mappers.UserMapper;
import com.example.kndemo.auth.repository.UserRepository;
import com.example.kndemo.auth.yaml.entity.User;
import com.example.kndemo.constants.CommonConstants;
import io.jsonwebtoken.*;
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
import java.util.concurrent.Callable;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthConfig authConfig;
    
    @Value("${jwt.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
         //secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //region Public API

    public UserDto signIn(CredentialsDto credentialsDto) {
        var user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
             return userMapper.toUserDto(user, createToken(user));
         }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto validateToken(String token) {
        return tryWithJwt(() -> {
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
        });
    }

    //region private API
    private String createToken(User user) {
        Claims claims = Jwts.claims()
                .setSubject(user.getLogin());
        fillCanReadClaim(user, claims);

        Date now = new Date();
        Date validity = new Date(now.getTime() + authConfig.getTokenExpirationTimeMs()); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private void fillCanReadClaim(User user,Claims claims) {
        claims.put(CommonConstants.JWT_CLAIM_CAN_EDIT_NAME,
                user.isCanEdit() ? CommonConstants.JWT_CLAIM_CAN_EDIT_TRUE
                        : CommonConstants.JWT_CLAIM_CAN_EDIT_FALSE);
    }

    private <V> V tryWithJwt(Callable<V> run) {
        try {
           return run.call();
        } catch (SignatureException e) {
            throw new AppException("Invalid JWT", HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException e) {
            throw new AppException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //endregion
}
