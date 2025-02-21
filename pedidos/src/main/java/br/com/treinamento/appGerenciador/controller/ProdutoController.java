package br.com.treinamento.appGerenciador.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.treinamento.appGerenciador.model.Produto;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoDadosAtualizacao;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoDadosCadastro;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoListagem;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoRespostaPaginada;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoSemPaginacao;
import br.com.treinamento.appGerenciador.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String descricao,
			@RequestParam(required = false) String categoria, 
			@RequestParam(required = false) Integer codigoBarras,
			@RequestParam(required = false) Double precoMinimo,
			@RequestParam(required = false) Double precoMaximo,
			@RequestParam(required = false, defaultValue = "true") String ativos,
	        @RequestParam(required = false, defaultValue = "id") String sortBy, 
	        @RequestParam(required = false, defaultValue = "asc") String direction) {
		
		List<String> validSortFields = Arrays.asList("id", "nome", "descricao", "categoria", "preco");
	    if (!validSortFields.contains(sortBy)) {
	        return ResponseEntity.badRequest().body("Campo de ordenação inválido.");
	    }

	    
	    Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		boolean ativo = Boolean.valueOf(ativos);
		
		 Pageable paginacaoComOrdenacao = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), 
		            sortDirection, sortBy); 
		
		var page = produtoRepository.findAllByFilters(nome, id, descricao, categoria, codigoBarras, precoMinimo, precoMaximo, ativo, paginacaoComOrdenacao).map(ProdutoListagem::new);
		
		
		ProdutoRespostaPaginada<ProdutoListagem> response = new ProdutoRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid ProdutoDadosCadastro dados, UriComponentsBuilder uriBuilder) {

		var produto = new Produto(dados);
		produtoRepository.save(produto);

		var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getIdProduto()).toUri();

		return ResponseEntity.created(uri).body(new ProdutoSemPaginacao(produto));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoDadosAtualizacao dados) {

		var produtoOptional = produtoRepository.findById(id);

		if (produtoOptional.get().getAtivo()) {

			var produto = produtoOptional.get();
			produto.atualizarInformacoes(dados);

			produtoRepository.save(produto);

			var resposta = new ProdutoSemPaginacao(produto);

			return ResponseEntity.ok(resposta);

		} else {

			return ResponseEntity.notFound().build();
		}

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		var produto = produtoRepository.getReferenceById(id);
		produto.getAtivo();

		if (produto.getAtivo()) {
			produto.excluir();
		} else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}/reativar")
	public ResponseEntity reativa(@PathVariable Long id) {
	    Produto produto = produtoRepository.findById(id).get();
	    if (produto == null) {
	        return ResponseEntity.notFound().build();
	    }
	    if (produto.getAtivo()){
	    	return ResponseEntity.ok("Já está ativo!");
	    }
	    
	    produto.setAtivo(true);
	    produtoRepository.save(produto);
	    return ResponseEntity.ok(produto);
	}


	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity pesquisar(@PathVariable Long id) {

		var produto = produtoRepository.findById(id);
		if (produto.get().getAtivo()) {

			var resposta = ResponseEntity.ok(new ProdutoSemPaginacao(produto.get()));
			return resposta;
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
