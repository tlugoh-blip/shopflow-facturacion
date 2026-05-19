package co.shopflow.facturacion.repository;

import co.shopflow.facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByClienteEmail(String email);
    Optional<Factura> findByPedidoId(Long pedidoId);
}