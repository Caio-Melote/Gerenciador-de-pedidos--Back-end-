package br.com.treinamento.appGerenciador.model;

import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosAtualizacao;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosCadastro;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosPlanilha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Vendedor")
@Table(name = "vendedor")
@NoArgsConstructor
@Getter
@Setter
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVendedor;
	private String nome;
	private String email;
	private String cpfVendedor;
	private String identificadorVend;
    private Long prefixoCepVend;
    private String cidadeVend;
    private String estadoVend;
	private boolean ativo;

	public Vendedor(VendedorDadosCadastro dados) {
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.cpfVendedor = dados.getCpfVendedor();
		this.identificadorVend = dados.getIdentificadorVend();
		this.prefixoCepVend = dados.getPrefixoCepVend();
		this.cidadeVend = dados.getCidadeVend();
		this.estadoVend = dados.getEstadoVend();
		this.ativo = true;
	}
	
	public Vendedor(Vendedor dados) {
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.cpfVendedor = dados.getCpfVendedor();
		this.ativo = true;
	}
	
	public Vendedor(VendedorDadosPlanilha dados) {
		  this.identificadorVend = dados.getIdentificadorVend();
	      this.prefixoCepVend = dados.getPrefixoCepVend();
	      this.cidadeVend = dados.getCidadeVend();
	      this.estadoVend = dados.getEstadoVend();
	    this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes(@Valid VendedorDadosAtualizacao dados) {

		if (dados.getNome() != null) {
			this.nome = dados.getNome();
		}

		if (dados.getEmail() != null) {
			this.email = dados.getEmail();
		}

		if (dados.getIdentificadorVend() != null) {
			this.identificadorVend = dados.getIdentificadorVend();
		}
		
		if (dados.getPrefixoCepVend() != null) {
			this.prefixoCepVend = dados.getPrefixoCepVend();
		}
		
		if (dados.getCidadeVend() != null) {
			this.cidadeVend = dados.getCidadeVend();
		}
		
		if (dados.getEstadoVend() != null) {
			this.estadoVend = dados.getEstadoVend();
		}
		
		if (dados.getCpfVendedor() != null) {
			this.cpfVendedor = dados.getCpfVendedor();
		}
		
		if (dados.isAtivo()) {
			this.ativo = dados.isAtivo();
		}

	}

}
