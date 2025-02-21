package br.com.treinamento.appGerenciador.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.treinamento.appGerenciador.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
	
	 @Query("SELECT v FROM Vendedor v "
	            + "WHERE (:nome IS NULL OR v.nome LIKE %:nome%) "
	            + "AND (:idVendedor IS NULL OR v.idVendedor = :idVendedor) "
	            + "AND (:email IS NULL OR v.email LIKE %:email%) "
	            + "AND (:cpfVendedor IS NULL OR v.cpfVendedor LIKE %:cpfVendedor%) "
	            + "AND (:ativo IS NULL OR v.ativo = %:ativo%) ")
	    Page<Vendedor> findAllByFilters(@Param("nome") String nome, 
	    		@Param("idVendedor") Long id,
	            @Param("email") String email,
	            @Param("cpfVendedor") String cpfVendedor,
	            @Param("ativo") boolean ativo,
	            Pageable pageable);
	 
	 Optional<Vendedor> findByIdVendedorAndAtivoTrue(Long id);
}
