package com.picpaysimplificado.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpaysimplificado.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

	private BigDecimal valor;
	private Usuario origem;
	private Usuario destino;
	private LocalDateTime timeStamp;

}
