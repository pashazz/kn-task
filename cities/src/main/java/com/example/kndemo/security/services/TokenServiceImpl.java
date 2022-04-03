package com.example.kndemo.security.services;

import com.example.kndemo.constants.CommonConstants;
import com.example.kndemo.services.TokenService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret-key:secret-key}")
    private String secretKey;

    @Override
    public boolean canEditCities(String token) {
        // TODO Cache it to reduce computations
        // Expired tokens won't be there anyway
        Optional<String> canEdit = Optional.<String>ofNullable(Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getOrDefault(CommonConstants.JWT_CLAIM_CAN_EDIT_NAME, null)
                .toString());


        if (canEdit.isEmpty()) {
            log.warn("Malformed JWS token - no edit role: {}", token);
            return false;
        }
        return canEdit
                .map(x -> x.equals(CommonConstants.JWT_CLAIM_CAN_EDIT_TRUE))
                .get();

    }
}
