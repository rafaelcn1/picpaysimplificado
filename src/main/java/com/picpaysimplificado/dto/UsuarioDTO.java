package com.picpaysimplificado.dto;

import java.math.BigDecimal;

import com.picpaysimplificado.model.TipoUsuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private String nomeCompleto;

	private String documento;

	private String email;

	private String password;

	@Enumerated(EnumType.STRING) // Para dizer que o campo é um dos valores Comum ou Lojista
	private TipoUsuario tipoUsuario;

	private BigDecimal carteira;
}
