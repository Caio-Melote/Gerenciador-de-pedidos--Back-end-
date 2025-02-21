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
import br.com.treinamento.appGerenciador.cliente.dto.ClienteDadosAtualizacao;
import br.com.treinamento.appGerenciador.cliente.dto.ClienteDadosCadastro;
import br.com.treinamento.appGerenciador.cliente.dto.ClienteListagem;
import br.com.treinamento.appGerenciador.cliente.dto.ClienteRespostaPaginada;
import br.com.treinamento.appGerenciador.cliente.dto.ClienteSemPaginacao;
import br.com.treinamento.appGerenciador.model.Cliente;
import br.com.treinamento.appGerenciador.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String nome, 
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String cpfCliente,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String telefone,
			@RequestParam(required = false) String endereco,
			@RequestParam(required = false, defaultValue = "true") String ativos,
			@RequestParam(required = false, defaultValue = "id") String sortBy, 
	        @RequestParam(required = false, defaultValue = "asc") String direction) {
		
		List<String> validSortFields = Arrays.asList("id", "nome", "email", "cpfCliente");
	    if (!validSortFields.contains(sortBy)) {
	        return ResponseEntity.badRequest().body("Campo de ordenação inválido.");
	    }


	    Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		boolean ativo = Boolean.valueOf(ativos);
		
		
		Pageable paginacaoComOrdenacao = PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize(), 
	            sortDirection, sortBy);  
		
		var page = clienteRepository.findAllByFilters(nome, email, cpfCliente, id, telefone, endereco, ativo, paginacaoComOrdenacao ).map(ClienteListagem::new);
		
		ClienteRespostaPaginada<ClienteListagem> response = new ClienteRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid ClienteDadosCadastro dados, UriComponentsBuilder uriBuilder) {

		Cliente cliente = new Cliente(dados);
		clienteRepository.save(cliente);

		var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getIdCliente()).toUri();

		return ResponseEntity.created(uri).body(new ClienteSemPaginacao(cliente));
	}
	

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDadosAtualizacao dados) {

		var clienteOptional = clienteRepository.findById(id);

		if (clienteOptional.get().isAtivo()) {

			var cliente = clienteOptional.get();
			cliente.atualizarInformacoes(dados);

			clienteRepository.save(cliente);

			var resposta = new ClienteSemPaginacao(cliente);

			return ResponseEntity.ok(resposta);

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		var cliente = clienteRepository.getReferenceById(id);
		cliente.isAtivo();

		if (cliente.isAtivo()) {
			cliente.excluir();
		} else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}/reativar")
	public ResponseEntity reativa(@PathVariable Long id) {
	    Cliente cliente = clienteRepository.findById(id).get();
	    if (cliente == null) {
	        return ResponseEntity.notFound().build();
	    }
	    if (cliente.isAtivo()){
	    	return ResponseEntity.ok("Já está ativo!");
	    }
	    
	    cliente.setAtivo(true);
	    clienteRepository.save(cliente);
	    return ResponseEntity.ok(cliente);
	}


	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity pesquisar(@PathVariable Long id) {

		var cliente = clienteRepository.findById(id);
		if (cliente.get().isAtivo()) {

			var resposta = ResponseEntity.ok(new ClienteSemPaginacao(cliente.get()));
			return resposta;
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
