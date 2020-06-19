package co.simplon.finalproject2020.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import co.simplon.finalproject2020.exception.InvalidJWTException;

/**
 * Specific filter that is in charge of checking that each HTTP request coming to our server contains a valid JWT.
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (InvalidJWTException e) {
        	// permet de garantir que le AppUser n'est pas authentifié
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid JWT provided");
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}



