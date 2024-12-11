package br.com.treinamento.appGerenciador.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.treinamento.appGerenciador.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	 @Query("SELECT c FROM Cliente c "
	            + "WHERE (:nome IS NULL OR c.nome LIKE %:nome%) "
	            + "AND (:email IS NULL OR c.email LIKE %:email%) "
	            + "AND (:cpfCliente IS NULL OR c.cpfCliente LIKE %:cpfCliente%) "
	            + "AND (:telefone IS NULL OR c.telefone LIKE %:telefone%) "
	            + "AND (:endereco IS NULL OR c.endereco LIKE %:endereco%) "
	            + "AND c.ativo = TRUE")
	    Page<Cliente> findAllByFilters(@Param("nome") String nome, 
	            @Param("email") String email,
	            @Param("cpfCliente") String cpfCliente,
	            @Param("telefone") String telefone,
	            @Param("endereco") String endereco,
	            Pageable pageable);
	 
	 Optional<Cliente> findByIdClienteAndAtivoTrue(Long id);
}
