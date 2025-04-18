package br.com.victor.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.victor.gestao_vagas.providers.JWTProviders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecutityFilter extends OncePerRequestFilter {

  @Autowired
  private JWTProviders jwtProviders;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    SecurityContextHolder.getContext().setAuthentication(null);
    String header = request.getHeader("Authorization");
    if (header != null ) {
      var subjectToken = this.jwtProviders.validadeToken(header);
      if (subjectToken.isEmpty()) {
        ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
      request.setAttribute("company_id", subjectToken);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken,
          Collections.emptyList());
      SecurityContextHolder.getContext().setAuthentication(auth);

    }

    filterChain.doFilter(request, response);
  }

}
