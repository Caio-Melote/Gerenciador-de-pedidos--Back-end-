package br.com.treinamento.appGerenciador.model;

import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosAtualizacao;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosCadastro;
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
	private long idVendedor;
	private String nome;
	private String email;
	private String cpf_vendedor;
	private boolean ativo;

	public Vendedor(VendedorDadosCadastro dados) {
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.cpf_vendedor = dados.getCpf_vendedor();
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

		if (dados.getCpf_vendedor() != null) {
			this.cpf_vendedor = dados.getCpf_vendedor();
		}

		if (dados.isAtivo()) {
			this.ativo = dados.isAtivo();
		}

	}

}