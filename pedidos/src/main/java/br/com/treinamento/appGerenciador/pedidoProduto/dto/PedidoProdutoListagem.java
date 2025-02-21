package br.com.treinamento.appGerenciador.pedidoProduto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	private String identificadorPedido;
    private Long identificadorItemPedido;
    private String identificadorVend;
    private String identificadorProd;
    private LocalDateTime dataEnvioLimite;
    private BigDecimal precoUnitario;
    private BigDecimal valorFrete;
	
	public PedidoProdutoListagem(PedidoProduto pedidoProduto) {
        this.idPedidoProduto = pedidoProduto.getIdPedidoProduto();
        this.pedido = pedidoProduto.getPedido();
        this.produto = pedidoProduto.getProduto();
        this.quantidade = pedidoProduto.getQuantidade();
        this.identificadorPedido = pedidoProduto.getIdentificadorPedido();
        this.identificadorItemPedido = pedidoProduto.getIdentificadorItemPedido();
        this.identificadorVend = pedidoProduto.getIdentificadorVend();
        this.identificadorProd = pedidoProduto.getIdentificadorProd();
        this.dataEnvioLimite = pedidoProduto.getDataEnvioLimite();
        this.precoUnitario = pedidoProduto.getPrecoUnitario();
        this.valorFrete = pedidoProduto.getValorFrete();
    }
}
