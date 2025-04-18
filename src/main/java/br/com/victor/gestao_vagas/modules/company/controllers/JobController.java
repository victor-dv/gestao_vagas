package br.com.victor.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.gestao_vagas.modules.company.dto.CreateJobDto;
import br.com.victor.gestao_vagas.modules.company.entities.JobEntity;
import br.com.victor.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
  @Autowired
  public CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public JobEntity creat(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    var jobEntity = JobEntity.builder()
        .description(createJobDto.getDescription())
        .companyId(UUID.fromString(companyId.toString()))
        .benefits(createJobDto.getBenefits())
        .level(createJobDto.getLevel())
        .build();
    return this.createJobUseCase.execute(jobEntity);

  }
}
