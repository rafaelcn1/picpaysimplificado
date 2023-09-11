package com.picpaysimplificado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	// Metodo para buscar por CPF ou CNPJ
	Optional<Usuario> findUsuarioByDocumento (String documento);
}
