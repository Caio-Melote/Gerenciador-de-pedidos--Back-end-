package br.com.treinamento.appGerenciador.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinamento.appGerenciador.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT p FROM Pedido p " 
		    + "LEFT JOIN p.vendedor v "
		    + "LEFT JOIN p.cliente c " 
		    + "WHERE (:cliente IS NULL OR c.nome LIKE %:cliente%) "
		    + "AND (:vendedor IS NULL OR v.nome LIKE %:vendedor%) "
		    + "AND (:idPedido IS NULL OR p.idPedido = :idPedido) "
		    + "AND (:dataStartCompra IS NULL OR p.dataCompra >= :dataStartCompra) "
		    + "AND (:dataEndCompra IS NULL OR p.dataCompra <= :dataEndCompra) "
		    + "AND (:dataStartEntrega IS NULL OR p.dataEntregaCliente >= :dataStartEntrega) "
		    + "AND (:dataEndEntrega IS NULL OR p.dataEntregaCliente <= :dataEndEntrega) "
		    + "AND (:valorTotal IS NULL OR p.valorTotal = :valorTotal) "
		    + "AND (:status IS NULL OR p.status LIKE %:status%) "
		    + "AND (:ativo IS NULL OR p.ativo = :ativo) ")
		Page<Pedido> findAllByFilters(@Param("cliente") String cliente, 
		    @Param("vendedor") String vendedor,
		    @Param("idPedido") Long id,
		    @Param("dataStartCompra") LocalDateTime dataStartCompra, 
		    @Param("dataEndCompra") LocalDateTime dataEndCompra,
		    @Param("dataStartEntrega") LocalDateTime dataStartEntrega, 
		    @Param("dataEndEntrega") LocalDateTime dataEndEntrega,
		    @Param("valorTotal") BigDecimal valorTotal, 
		    @Param("status") String status, 
		    @Param("ativo") Boolean ativo,
		    Pageable pageable);


	
	Optional<Pedido> findByIdPedidoAndAtivoTrue(Long id);
}
