package br.com.victor.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.victor.gestao_vagas.modules.company.dto.AuthCompanyDto;
import br.com.victor.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());
    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("JAVAGAS")
        .withSubject(company.getId().toString())
        .sign(algorithm);

    return token;

  }
}
