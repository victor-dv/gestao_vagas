package br.com.victor.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.gestao_vagas.modules.company.entities.JobEntity;
import br.com.victor.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
  @Autowired
  public CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public JobEntity creat(@Valid @RequestBody JobEntity jobEntity){
    return this.createJobUseCase.execute(jobEntity);

  }
}
