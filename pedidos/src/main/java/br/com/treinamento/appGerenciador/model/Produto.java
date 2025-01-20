package br.com.treinamento.appGerenciador.model;

import java.math.BigDecimal;

import br.com.treinamento.appGerenciador.produto.dto.ProdutoDadosAtualizacao;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoDadosCadastro;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoDadosPlanilha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Produto")
@Table(name = "produto")
@NoArgsConstructor
@Getter
@Setter
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;  
    private String nome;
    private String descricao;
    private BigDecimal preco;  
    private String categoria;  
    private Integer codigoBarras;   
  
    private String identificador_prod;
    private Long tamanho_nome;
    private Long tamanho_descricao;
    private Long quantidade_fotos;
    private Long peso_gramas;
    private Long comprimento_cm;
    private Long altura_cm;
    private Long largura_cm;
    private Boolean ativo; 
    
    public Produto(ProdutoDadosCadastro dados) {
        this.nome = dados.getNome();
        this.descricao = dados.getDescricao();
        this.preco = dados.getPreco();  
        this.categoria = dados.getCategoria();  
        this.codigoBarras = dados.getCodigoBarras();
        this.ativo = true;
    	}
    
    public Produto(ProdutoDadosPlanilha dados) {
    	this.identificador_prod = dados.getIdentificador_prod();
    	this.categoria = dados.getCategoria();
    	this.tamanho_nome = dados.getTamanho_nome();
    	this.tamanho_descricao = dados.getTamanho_descricao();
    	this.quantidade_fotos = dados.getQuantidade_fotos();
    	this.peso_gramas = dados.getPeso_gramas();
    	this.comprimento_cm = dados.getComprimento_cm();
    	this.altura_cm = dados.getAltura_cm();
    	this.largura_cm = dados.getLargura_cm();
        this.ativo = true;
    	}
    
    public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes(@Valid ProdutoDadosAtualizacao dados) {
		
		if (dados.getNome() != null) {
			this.nome = dados.getNome();
		}
		
		if (dados.getDescricao() != null) {
			this.descricao = dados.getDescricao();
		}
		
		if (dados.getPreco() != null) {
			this.preco = dados.getPreco();
		}
		
		if (dados.getCategoria() != null) {
			this.categoria = dados.getCategoria();
		}
		
		if (dados.getCodigoBarras() != null) {
			this.codigoBarras = dados.getCodigoBarras();
		}
		
		if (dados.isAtivo()) {
			this.ativo = dados.isAtivo();
		}
		
	}
    
    
    }

