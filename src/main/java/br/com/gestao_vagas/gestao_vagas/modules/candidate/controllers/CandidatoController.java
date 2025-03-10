package br.com.gestao_vagas.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_vagas.gestao_vagas.modules.candidate.CandidatoEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

	@PostMapping("/")
	public void create(@Valid @RequestBody CandidatoEntity cantidadeEntity) {
		System.out.println("Candidato");
		System.out.println(cantidadeEntity.getEmail());
	}
}
