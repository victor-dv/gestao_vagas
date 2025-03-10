package br.com.gestao_vagas.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidatoEntity {

	
	private UUID id;
	private String name;
	@NotBlank()
	@Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
	private String username;
	@Email(message = "O corpo [email] deve conter um email válido")
	private String email;
	@Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
	private String password;
	private String description;
	private String curriculom;
}
