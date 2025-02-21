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
    private String identificadorProd;
    private Long tamanhoNome;
    private Long tamanhoDescricao;
    private Long quantidadeFotos;
    private Long pesoGramas;
    private Long comprimentoCm;
    private Long alturaCm;
    private Long larguraCm;
    private Boolean ativo; 
    
    public Produto(ProdutoDadosCadastro dados) {
        this.nome = dados.getNome();
        this.descricao = dados.getDescricao();
        this.preco = dados.getPreco();  
        this.categoria = dados.getCategoria();  
        this.codigoBarras = dados.getCodigoBarras();
        this.identificadorProd = dados.getIdentificadorProd();
        this.tamanhoNome = dados.getTamanhoNome();
        this.tamanhoDescricao = dados.getTamanhoDescricao();
        this.quantidadeFotos = dados.getQuantidadeFotos();
        this.pesoGramas = dados.getPesoGramas();
        this.comprimentoCm = dados.getComprimentoCm();
        this.alturaCm = dados.getAlturaCm();
        this.larguraCm = dados.getAlturaCm();
        this.ativo = true;
    	}
    
    public Produto(ProdutoDadosPlanilha dados) {
    	this.identificadorProd = dados.getIdentificadorProd();
    	this.categoria = dados.getCategoria();
    	this.tamanhoNome = dados.getTamanhoNome();
    	this.tamanhoDescricao = dados.getTamanhoDescricao();
    	this.quantidadeFotos = dados.getQuantidadeFotos();
    	this.pesoGramas = dados.getPesoGramas();
    	this.comprimentoCm = dados.getComprimentoCm();
    	this.alturaCm = dados.getAlturaCm();
    	this.larguraCm = dados.getLarguraCm();
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
		
        if (dados.getIdentificadorProd() != null) {
			this.identificadorProd = dados.getIdentificadorProd();
		}
		
		if (dados.getTamanhoNome() != null) {
			this.tamanhoNome = dados.getTamanhoNome();
		}
		
		if (dados.getTamanhoDescricao() != null) {
			this.tamanhoDescricao = dados.getTamanhoDescricao();
		}
		
		if (dados.getQuantidadeFotos() != null) {
			this.quantidadeFotos = dados.getQuantidadeFotos();
		}
		
		if (dados.getPesoGramas() != null) {
			this.pesoGramas = dados.getPesoGramas();
		}
		
		if (dados.getComprimentoCm() != null) {
			this.comprimentoCm = dados.getComprimentoCm();
		}
		
		if (dados.getAlturaCm() != null) {
			this.alturaCm = dados.getAlturaCm();
		}
		
		if (dados.getAlturaCm() != null) {
			this.larguraCm = dados.getAlturaCm();
		}
		
		if (dados.isAtivo()) {
			this.ativo = dados.isAtivo();
		}
		
	}
    
    
    }

