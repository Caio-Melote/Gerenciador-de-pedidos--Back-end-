package br.com.treinamento.appGerenciador.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.PedidoProduto;
import br.com.treinamento.appGerenciador.model.Produto;
import br.com.treinamento.appGerenciador.pedidoProduto.dto.PedidoProdutoDadosAtualizacao;
import br.com.treinamento.appGerenciador.pedidoProduto.dto.PedidoProdutoDadosCadastro;
import br.com.treinamento.appGerenciador.pedidoProduto.dto.PedidoProdutoListagem;
import br.com.treinamento.appGerenciador.pedidoProduto.dto.PedidoProdutoRespostaPaginada;
import br.com.treinamento.appGerenciador.pedidoProduto.dto.PedidoProdutoSemPaginacao;
import br.com.treinamento.appGerenciador.repository.PedidoProdutoRepository;
import br.com.treinamento.appGerenciador.service.PedidoProdutoService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedidoproduto")
public class PedidoProdutoController {

	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;
	
	 @Autowired
	 private PedidoProdutoService pedidoProdutoService;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) Long pedido, 
			@RequestParam(required = false) Long produto,
			@RequestParam(required = false) Integer quantidade, 
			@RequestParam(required = false) BigDecimal precoMin,
			@RequestParam(required = false) BigDecimal precoMax, 
			@RequestParam(required = false) BigDecimal desconto) {

		var page = pedidoProdutoRepository
				.findAllByFilters(pedido, produto, quantidade, precoMin, precoMax, desconto, paginacao)
				.map(PedidoProdutoListagem::new);

		PedidoProdutoRespostaPaginada<PedidoProdutoListagem> response = new PedidoProdutoRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid PedidoProdutoDadosCadastro dados, UriComponentsBuilder uriBuilder) {

		Pedido pedido = pedidoProdutoService.validarPedidoAtivo(dados.getIdPedido());
        Produto produto = pedidoProdutoService.validarProdutoAtivo(dados.getIdProduto());

        PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, dados.getQuantidade(), dados.getDesconto());
        pedidoProdutoService.salvarPedidoProduto(pedidoProduto);

        var uri = uriBuilder.path("/pedidoproduto/{id}").buildAndExpand(pedidoProduto.getIdPedidoProduto()).toUri();
        return ResponseEntity.created(uri).body(new PedidoProdutoSemPaginacao(pedidoProduto));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid PedidoProdutoDadosAtualizacao dados) {

		var pedidoProdutoOptional = pedidoProdutoRepository.findById(id);
		
		if (pedidoProdutoOptional.isPresent() && pedidoProdutoOptional.get().getAtivo()) {
            Pedido pedidoExistente = pedidoProdutoService.validarPedidoAtivo(dados.getIdPedido());
            Produto produtoExistente = pedidoProdutoService.validarProdutoAtivo(dados.getIdProduto());

            PedidoProduto pedidoProduto = pedidoProdutoOptional.get();
            pedidoProduto.atualizarInformacoes(pedidoExistente, produtoExistente, dados.getQuantidade(), dados.getPrecoUnitario(), dados.getDesconto());

            pedidoProdutoService.salvarPedidoProduto(pedidoProduto);

            var resposta = new PedidoProdutoSemPaginacao(pedidoProduto);
            return ResponseEntity.ok(resposta);
        }

        return ResponseEntity.notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		PedidoProduto pedidoProduto = pedidoProdutoRepository.getReferenceById(id);
		pedidoProduto.getAtivo();

		if (pedidoProduto.getAtivo()) {
			pedidoProduto.excluir();
		} else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}/reativar")
	public ResponseEntity reativa(@PathVariable Long id) {
	    PedidoProduto pedidoProduto = pedidoProdutoRepository.findById(id).get();
	    if (pedidoProduto == null) {
	        return ResponseEntity.notFound().build();
	    }
	    if (pedidoProduto.getAtivo()){
	    	return ResponseEntity.ok("Já está ativo!");
	    }
	    
	    pedidoProduto.setAtivo(true);
	    pedidoProdutoRepository.save(pedidoProduto);
	    return ResponseEntity.ok(pedidoProduto);
	}


	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity pesquisar(@PathVariable Long id) {

		PedidoProduto pedidoProduto = pedidoProdutoRepository.findById(id).get();
		if (pedidoProduto.getAtivo()) {

			var resposta = ResponseEntity.ok(new PedidoProdutoSemPaginacao(pedidoProduto));
			return resposta;
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
