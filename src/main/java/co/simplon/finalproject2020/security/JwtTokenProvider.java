package co.simplon.finalproject2020.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import co.simplon.finalproject2020.exception.InvalidJWTException;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.enums.ProfilUtilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	// on r�cup�re le secret dans le fichier properties
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    // ici on met la valeur par d�faut
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private UserDetailsService userDetailsService;
    
    
    
    /**
     * Cette m�thode d'initialisation s'�x�cute avant le constructeur
     * Elle encode notre code secret en base64 pour la transmission dans le header
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    
    
    /**
     * Method that creates a token with username as "sub" field, user roles as "auth" field, "iat" as now date,
     * "exp" as now date + validity time.
     * @param username the user username.
     * @param roles the user roles.
     * @return the created JWT as String.
     */
    public String createToken(String idrh, Collection<? extends GrantedAuthority> roles) {
    	
    	System.out.println("on est entr� dans la m�thode createToken du JwtProvider avec en parametre idrh = " + idrh + " et roles = " + roles);
    	
    	Utilisateur subject = (Utilisateur) userDetailsService.loadUserByUsername(idrh);
    	
    	System.out.println("On a bien r�cup�r� un Utilisateur. Verifier que idTeam et estActif ne sont pas Null ! : " + subject);

        Claims claims = Jwts.claims().setSubject(idrh);
        claims.put("auth", roles.stream().map(s -> s.getAuthority()).filter(Objects::nonNull).collect(Collectors.toList()));//roles /*roles.stream().map(s -> s.getAuthority()).filter(Objects::nonNull).collect(Collectors.toList())*/);
        claims.put("name", (subject.getNom() + " " + subject.getPrenom()));
        claims.put("team", subject.getEquipe().getLibelle());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }
    
    /**
     * Method that returns the user authentication based on one JWT.
     * @param token the token to use for authentication.
     * @return the authentication object if username is found.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(extractIdrh(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    
    /**
     * Method that gets the username from the JWT.
     * @param token the token to analyse.
     * @return the user username as String.
     */
    public String extractIdrh(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    
    
    /**
     * Method that resolve a JWT from an HTTP Request.
     * The header should contains an Authorization field where JWT should be added after "Bearer ".
     * @param req the request to check.
     * @return the JWT from the HTTP Header.
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    
    /**
     * Method that checks that a JWT is valid.
     * The signature should be correct and the exp time should be after "now"
     * @param token the token to validate
     * @return True if the token is valid, throws InvalidJWTException otherwise.
     * @throws InvalidJWTException
     */
    public boolean validateToken(String token) throws InvalidJWTException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJWTException();
        }
    }
    
    
}
