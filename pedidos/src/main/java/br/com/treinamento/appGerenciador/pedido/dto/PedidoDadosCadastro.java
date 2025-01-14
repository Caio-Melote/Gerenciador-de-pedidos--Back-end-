package br.com.treinamento.appGerenciador.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PedidoDadosCadastro {
	
	@NotNull
	private Long idCliente;

	@NotNull
	private Long idVendedor;
	
	@NotNull
	private LocalDate data;

	private BigDecimal valorTotal;
	
	@NotBlank
	private String status;
	
	@NotNull
	private boolean ativo;
}	
