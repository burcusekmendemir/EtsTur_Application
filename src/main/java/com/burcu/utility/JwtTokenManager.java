package com.burcu.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.utility.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

   // @Value("${jwt.secretKey}")
    private final String secretKey = "gqWaaif30M8kQ1TEL7OZNZSjbdbcsHpsmuxhCZm4q6I5AeFLkk";
   // @Value("${jwt.issuer}")
    private final String issuer ="burcuser";
   // @Value("${jwt.audience}")
    private final String audience ="bilgeadam";
    //ortam değişkeni ekleyince program kapatılıp açılır yoksa çalışmaz

    public Optional<String> createToken(String id){
        String token= null;
        Date date=new Date(System.currentTimeMillis()+(1000*60*5)); //5 dklık date
        try {
            token= JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id", id)
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    //Rolüne göre yetkisine göre token yaratma
    public Optional<String> createToken(String id, ERole role){
        String token= null;
        Date date=new Date(System.currentTimeMillis()+(1000*60*5)); //5 dklık date
        try {
            token= JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id", id)
                    .withClaim("role", role.toString())
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


    public Boolean validateToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey); //kullanacağım algoritmayı bir değiken içerisinde verdik
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build(); //bu algortimayla atanmış atamasını burcu ve bilgeadama atanmış olan token var mı?
            DecodedJWT decodedJWT= verifier.verify(token); //burada bir değer dönecek true ya da false
            if (decodedJWT==null){
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
        return true;
    }

    public Optional<String> getIdFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT= verifier.verify(token);

            if (decodedJWT==null){
                throw new OtelException(ErrorType.INVALID_TOKEN);
            }

            String id=decodedJWT.getClaim("id").asString();
            return Optional.of(id);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
    }

    public Optional<String> getRoleFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null) {
                throw new OtelException(ErrorType.INVALID_TOKEN);
            }

            String role = decodedJWT.getClaim("role").asString();
            return Optional.of(role);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new OtelException(ErrorType.INVALID_TOKEN);
        }
    }


}
