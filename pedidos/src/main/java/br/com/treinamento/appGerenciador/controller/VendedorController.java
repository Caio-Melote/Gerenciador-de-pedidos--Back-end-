package br.com.treinamento.appGerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.treinamento.appGerenciador.model.Vendedor;

import br.com.treinamento.appGerenciador.produto.dto.ProdutoRespostaPaginada;

import br.com.treinamento.appGerenciador.produto.dto.VendedorListagem;
import br.com.treinamento.appGerenciador.repository.VendedorRepository;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosCadastro;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorSemPaginacao;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private VendedorRepository vendedorRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String email,
			@RequestParam(required = false) String cpf_vendedor) {

		var page = vendedorRepository.findAllByFilters(nome, email, cpf_vendedor, paginacao).map(VendedorListagem::new);

		ProdutoRespostaPaginada<VendedorListagem> response = new ProdutoRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid VendedorDadosCadastro dados, UriComponentsBuilder uriBuilder) {

		Vendedor vendedor = new Vendedor(dados);
		vendedorRepository.save(vendedor);

		var uri = uriBuilder.path("/vendedor/{id}").buildAndExpand(vendedor.getIdVendedor()).toUri();

		return ResponseEntity.created(uri).body(new VendedorSemPaginacao(vendedor));
	}
}
