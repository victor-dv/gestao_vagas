package br.com.victor.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.gestao_vagas.exceptions.UserFoundException;
import br.com.victor.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.victor.gestao_vagas.modules.company.useCases.CreateCompompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompompanyUseCase createCompanyUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok().body(result);
    } catch (UserFoundException e) {
      // TODO: handle exception
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());

    }
  }

}