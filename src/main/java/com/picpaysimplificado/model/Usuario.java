package com.picpaysimplificado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data // Parar Criar todos os gets, sets, equals, toString e Hashcode
@AllArgsConstructor // Parar criar o construtor que aceita todos os campos da classe como
					// argumentos.
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nomeCompleto;

	@Column(unique = true)
	private String documento;

	@Column(unique = true)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING) // Para dizer que o campo é um dos valores Comum ou Lojista
	private TipoUsuario tipoUsuario;

	private Double carteira;

}
