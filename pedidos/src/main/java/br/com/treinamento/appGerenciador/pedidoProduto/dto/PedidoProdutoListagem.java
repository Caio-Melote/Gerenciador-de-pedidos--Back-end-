package br.com.treinamento.appGerenciador.pedidoProduto.dto;

import java.math.BigDecimal;
import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.PedidoProduto;
import br.com.treinamento.appGerenciador.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoProdutoListagem {
	

	private Long idPedidoProduto;
	private Pedido pedido;
	private Produto produto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private Boolean ativo;
	
	public PedidoProdutoListagem(PedidoProduto pedidoProduto) {
        this.idPedidoProduto = pedidoProduto.getIdPedidoProduto();
        this.pedido = pedidoProduto.getPedido();
        this.produto = pedidoProduto.getProduto();
        this.quantidade = pedidoProduto.getQuantidade();
        this.precoUnitario = pedidoProduto.getPrecoUnitario();
    }
}
