package br.com.treinamento.appGerenciador.produto.dto;

import java.math.BigDecimal;
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
    private BigDecimal preco;  
    private String categoria;  
    private Integer codigoBarras;   
    private String identificadorProd;
    private Long tamanhoNome;
    private Long tamanhoDescricao;
    private Long quantidadeFotos;
    private Long pesoGramas;
    private Long comprimentoCm;
    private Long alturaCm;
    private Long larguraCm;
    private boolean ativo; 
}
