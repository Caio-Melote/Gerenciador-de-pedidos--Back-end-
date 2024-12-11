package br.com.treinamento.appGerenciador.pedidoProduto.dto;

import java.util.List;
import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoRespostaPaginada<T> {
	
	private List<T> data;
	private int start;
	private int limit;
	private int totalPage;
	private long size;
	
	public PedidoProdutoRespostaPaginada(Page<T> page) {
		this.data = page.getContent();
		this.start = page.getNumber();
		this.limit = page.getSize();
		this.totalPage = page.getTotalPages();
		this.size = page.getTotalElements();
	}
}
