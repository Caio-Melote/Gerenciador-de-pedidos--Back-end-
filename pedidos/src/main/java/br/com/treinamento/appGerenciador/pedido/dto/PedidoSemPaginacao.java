package br.com.treinamento.appGerenciador.pedido.dto;

import br.com.treinamento.appGerenciador.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoSemPaginacao {
	private Pedido data;
}
