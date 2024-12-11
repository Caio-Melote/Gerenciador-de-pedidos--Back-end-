package br.com.treinamento.appGerenciador.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinamento.appGerenciador.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT p FROM Pedido p " 
	+ "WHERE (:cliente IS NULL OR p.cliente.idCliente = :cliente) "
			+ "AND (:vendedor IS NULL OR p.vendedor.idVendedor = :vendedor) "
			+ "AND (:dataStart IS NULL OR p.data >= :dataStart) "
			+ "AND (:dataEnd IS NULL OR p.data <= :dataEnd) "
			+ "AND (:valorTotal IS NULL OR p.valorTotal = :valorTotal) "
			+ "AND (:status IS NULL OR p.status LIKE %:status%) " 
			+ "AND p.ativo = TRUE")
	Page<Pedido> findAllByFilters(@Param("cliente") String cliente, 
			@Param("vendedor") String vendedor,
			@Param("dataStart") LocalDate dataStart, 
			@Param("dataEnd") LocalDate dataEnd,
			@Param("valorTotal") BigDecimal valorTotal, 
			@Param("status") String status, Pageable pageable);
	
	Optional<Pedido> findByIdPedidoAndAtivoTrue(Long id);
}
