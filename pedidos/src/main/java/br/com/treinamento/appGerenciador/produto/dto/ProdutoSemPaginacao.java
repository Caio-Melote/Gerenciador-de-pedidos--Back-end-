package br.com.treinamento.appGerenciador.produto.dto;

import br.com.treinamento.appGerenciador.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoSemPaginacao {
	private Produto data;
}
