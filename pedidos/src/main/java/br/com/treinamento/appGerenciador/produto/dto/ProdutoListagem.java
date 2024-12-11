package br.com.treinamento.appGerenciador.produto.dto;

import java.math.BigDecimal;

import br.com.treinamento.appGerenciador.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProdutoListagem {
	
	private Long idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;  
    private String categoria; 
    private Integer codigoBarras; 

    public ProdutoListagem(Produto produto) {
        this.idProduto = produto.getIdProduto(); 
        this.nome = produto.getNome();    
        this.descricao = produto.getDescricao(); 
        this.preco = produto.getPreco();   
        this.categoria = produto.getCategoria();
        this.codigoBarras = produto.getCodigoBarras();
    }
}

