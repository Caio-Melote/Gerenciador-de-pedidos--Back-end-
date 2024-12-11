package br.com.treinamento.appGerenciador.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "PedidoProduto")
@Table(name = "pedidoProduto")
@NoArgsConstructor
@Getter
@Setter
public class PedidoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedidoProduto;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal desconto;
	private Boolean ativo;

	public PedidoProduto(Pedido pedido, Produto produto, Integer quantidade, BigDecimal desconto) {

		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = produto.getPreco();
		this.desconto = desconto;
		this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes( Pedido pedido,  Produto produto,  Integer quantidade,  BigDecimal precoUnitario,  BigDecimal desconto) {

		if (pedido != null) {
			this.pedido = pedido;
		}

		if (produto != null) {
			this.produto = produto;
		}

		if (quantidade != null) {
			this.quantidade = quantidade;
		}

		if (precoUnitario != null) {
			this.precoUnitario = precoUnitario;
		}

		if (desconto != null) {
			this.desconto = desconto;
		}

	}

}
