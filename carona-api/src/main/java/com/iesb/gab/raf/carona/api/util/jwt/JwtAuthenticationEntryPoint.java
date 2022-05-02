package com.iesb.gab.raf.carona.api.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iesb.gab.raf.carona.api.payload.response.ApiErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        apiErrorResponse.setError("Unauthorized");
        apiErrorResponse.setMessage(authException.getMessage());
        apiErrorResponse.setPath(request.getServletPath());
        apiErrorResponse.setDetails(null);
        apiErrorResponse.setTimestamp(Instant.now().toString());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), apiErrorResponse);
    }
}
