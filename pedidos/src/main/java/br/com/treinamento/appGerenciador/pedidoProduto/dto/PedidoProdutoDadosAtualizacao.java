package br.com.treinamento.appGerenciador.pedidoProduto.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PedidoProdutoDadosAtualizacao {
	
	@NotNull
	private Long idPedido;
	
	@NotNull
	private Long idProduto;
	

	private Integer quantidade;
	
	
	private BigDecimal precoUnitario;
	

	private BigDecimal desconto;
}
