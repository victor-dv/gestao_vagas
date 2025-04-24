package br.com.victor.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCandidateRequestDTO(String username, String password) {


}
