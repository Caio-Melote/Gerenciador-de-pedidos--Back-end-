package br.com.treinamento.appGerenciador.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDadosAtualizacao {
	
	private long idProduto;  
    private String nome;
    private String descricao;
    private double preco;  
    private String categoria;  
    private Integer codigoBarras;   
    private boolean ativo; 
}
