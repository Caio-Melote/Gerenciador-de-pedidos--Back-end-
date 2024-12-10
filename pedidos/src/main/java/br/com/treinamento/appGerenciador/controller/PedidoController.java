package br.com.treinamento.appGerenciador.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import br.com.treinamento.appGerenciador.model.Cliente;
import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.Vendedor;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosAtualizacao;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosCadastro;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoListagem;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoRespostaPaginada;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoSemPaginacao;
import br.com.treinamento.appGerenciador.repository.ClienteRepository;
import br.com.treinamento.appGerenciador.repository.PedidoRepository;
import br.com.treinamento.appGerenciador.repository.VendedorRepository;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String cliente, @RequestParam(required = false) String vendedor,
			@RequestParam(required = false) LocalDate dataStart, @RequestParam(required = false) LocalDate dataEnd,
			@RequestParam(required = false) BigDecimal valorTotal, @RequestParam(required = false) String status) {

		var page = pedidoRepository
				.findAllByFilters(cliente, vendedor, dataStart, dataEnd, valorTotal, status, paginacao)
				.map(PedidoListagem::new);

		PedidoRespostaPaginada<PedidoListagem> response = new PedidoRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid PedidoDadosCadastro dados, UriComponentsBuilder uriBuilder) {
	
		Cliente clienteExistente = clienteRepository.findById(dados.getIdCliente()).orElseThrow(() -> new IllegalArgumentException("Cliente com ID = " + dados.getIdCliente() + " não encontrado!!"));
		
		Vendedor vendedorExistente = vendedorRepository.findById(dados.getIdVendedor()).orElseThrow(() -> new IllegalArgumentException("Vendedor com ID = " + dados.getIdVendedor() + " não encontrado!!"));	
		
		var pedido = new Pedido(dados); 
		pedido.setCliente(clienteExistente);
		pedido.setVendedor(vendedorExistente); 
		pedidoRepository.save(pedido); 
		
		var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getIdPedido()).toUri(); 
		return ResponseEntity.created(uri).body(new PedidoSemPaginacao(pedido));
}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid PedidoDadosAtualizacao dados) {

		var pedidoOptional = pedidoRepository.findById(id);
		
		Cliente clienteExistente = clienteRepository.findById(dados.getIdCliente()).orElseThrow(() -> new IllegalArgumentException("Cliente com ID = " + dados.getIdCliente() + " não encontrado!!"));
		
		Vendedor vendedorExistente = vendedorRepository.findById(dados.getIdVendedor()).orElseThrow(() -> new IllegalArgumentException("Vendedor com ID = " + dados.getIdVendedor() + " não encontrado!!"));	


		if (pedidoOptional.get().getAtivo()) {

			var pedido = pedidoOptional.get();
			pedido.atualizarInformacoes(dados);
			pedido.setCliente(clienteExistente);
			pedido.setVendedor(vendedorExistente); 

			pedidoRepository.save(pedido);

			var resposta = new PedidoSemPaginacao(pedido);

			return ResponseEntity.ok(resposta);

		} else {

			return ResponseEntity.notFound().build();
		}

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		Pedido pedido = pedidoRepository.getReferenceById(id);
		pedido.getAtivo();

		if (pedido.getAtivo()) {
			pedido.excluir();
		} else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}/reativar")
	public ResponseEntity reativa(@PathVariable Long id) {
	    Pedido pedido = pedidoRepository.findById(id).get();
	    if (pedido == null) {
	        return ResponseEntity.notFound().build();
	    }
	    if (pedido.getAtivo()){
	    	return ResponseEntity.ok("Já está ativo!");
	    }
	    
	    pedido.setAtivo(true);
	    pedidoRepository.save(pedido);
	    return ResponseEntity.ok(pedido);
	}


	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity pesquisar(@PathVariable Long id) {

		Pedido pedido = pedidoRepository.findById(id).get();
		if (pedido.getAtivo()) {

			var resposta = ResponseEntity.ok(new PedidoSemPaginacao(pedido));
			return resposta;
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
