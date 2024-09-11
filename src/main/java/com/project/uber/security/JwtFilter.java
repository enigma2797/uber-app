package com.project.uber.security;

import com.project.uber.entities.User;
import com.project.uber.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try
        {
            String requestTokenHeader = request.getHeader("Authorization");
            if(requestTokenHeader == null || !requestTokenHeader.contains("Bearer"))
            {
                //throw new AuthenticationServiceException("Bearer token not found");
                filterChain.doFilter(request, response);
                return;
            }

            String token = requestTokenHeader.split("Bearer ")[1];

            Long userId = jwtService.getUserFromToken(token);

            if(userId != null &&  SecurityContextHolder.getContext().getAuthentication()==null)
            {
                User user = userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

            filterChain.doFilter(request, response);
        }

        catch(Exception e)
        {
            exceptionResolver.resolveException(request, response, null, e);
        }

    }
}
