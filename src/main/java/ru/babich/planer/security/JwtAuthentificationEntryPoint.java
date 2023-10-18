package ru.babich.planer.security;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.babich.planer.payload.response.InvalidLoginResponse;

import java.io.IOException;

@Component
public class JwtAuthentificationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

            InvalidLoginResponse invalidLoginResponce = new InvalidLoginResponse();
            String jsonLoginReponce = new Gson().toJson(invalidLoginResponce);

            response.setContentType(SecurityConstants.CONTENT_TYPE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().println(jsonLoginReponce);

    }
}
