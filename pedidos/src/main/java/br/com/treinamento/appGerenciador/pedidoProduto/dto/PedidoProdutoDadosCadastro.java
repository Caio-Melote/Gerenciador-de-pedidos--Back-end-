package br.com.treinamento.appGerenciador.pedidoProduto.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PedidoProdutoDadosCadastro {

	@NotNull
	private Long idPedido;

	@NotNull
	private Long idProduto;

	@NotNull
	private Integer quantidade;
	
	@NotNull
	private boolean ativo;
}	

