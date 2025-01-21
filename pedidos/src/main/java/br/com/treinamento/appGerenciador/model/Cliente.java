package br.com.treinamento.appGerenciador.model;

import br.com.treinamento.appGerenciador.cliente.dto.ClienteDadosAtualizacao;
import br.com.treinamento.appGerenciador.cliente.dto.ClienteDadosCadastro;
import br.com.treinamento.appGerenciador.cliente.dto.ClienteDadosPlanilha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Cliente")
@Table(name = "cliente")
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	private String nome;
	private String email;
	private String cpfCliente;
	private String telefone;
	private String endereco;
	private String identificadorCliente;
	private String idUnicoCliente;
	private Long prefixoCepCliente;
	private String cidadeCliente;
	private String estadoCliente;
	private boolean ativo;

	public Cliente(ClienteDadosCadastro dados) {
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.cpfCliente = dados.getCpfCliente();
		this.telefone = dados.getTelefone();
		this.endereco = dados.getEndereco();
		this.ativo = true;
	}
	
	public Cliente(ClienteDadosPlanilha dados) {
		this.identificadorCliente = dados.getIdentificadorCliente();
		this.idUnicoCliente = dados.getIdUnicoCliente();
		this.prefixoCepCliente = dados.getPrefixoCepCliente();
		this.cidadeCliente = dados.getCidadeCliente();
		this.estadoCliente = dados.getEstadoCliente();
		this.ativo = true;
	}
	
	public Cliente(Cliente dados) {
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.cpfCliente = dados.getCpfCliente();
		this.telefone = dados.getTelefone();
		this.endereco = dados.getEndereco();
		this.ativo = true;
	}


	public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes(@Valid ClienteDadosAtualizacao dados) {

		if (dados.getNome() != null) {
			this.nome = dados.getNome();
		}

		if (dados.getEmail() != null) {
			this.email = dados.getEmail();
		}

		if (dados.getCpfCliente() != null) {
			this.cpfCliente = dados.getCpfCliente();
		}
		
		if (dados.getTelefone() != null) {
			this.telefone = dados.getTelefone();
		}
		
		if (dados.getEndereco() != null) {
			this.endereco = dados.getEndereco();
		}

		if (dados.isAtivo()) {
			this.ativo = dados.isAtivo();
		}

	}

}
