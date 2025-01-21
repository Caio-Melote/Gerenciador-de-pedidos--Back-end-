package br.com.treinamento.appGerenciador.pedido.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDadosPlanilha {

	@NotBlank
	String identificadorPedido;

	@NotBlank
	String identificadorCliente;
	
	@NotBlank
	String status;

	@NotNull
	LocalDateTime dataCompra;

	@NotNull
	LocalDateTime dataAprovacao;

	@NotNull
	LocalDateTime dataEntregaTransportadora;

	@NotNull
	LocalDateTime dataEntregaCliente;

	@NotNull
	LocalDateTime dataEntregaEstimada;
}
