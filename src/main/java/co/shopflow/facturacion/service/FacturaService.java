package co.shopflow.facturacion.service;

import co.shopflow.facturacion.model.Factura;
import co.shopflow.facturacion.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;

    // COMMAND: Generar factura desde evento de pedido
    public Factura generarFactura(Map<String, Object> pedido) {
        Long pedidoId = Long.valueOf(pedido.get("id").toString());

        // Verificar si ya existe factura para este pedido
        if (facturaRepository.findByPedidoId(pedidoId).isPresent()) {
            return facturaRepository.findByPedidoId(pedidoId).get();
        }

        Factura factura = new Factura();
        factura.setPedidoId(pedidoId);
        factura.setClienteNombre(pedido.get("clienteNombre").toString());
        factura.setClienteEmail(pedido.get("clienteEmail").toString());

        Double subtotal = Double.valueOf(pedido.get("total").toString());
        Double iva = subtotal * 0.19;
        Double total = subtotal + iva;

        factura.setSubtotal(subtotal);
        factura.setIva(iva);
        factura.setTotal(total);
        factura.setEstado("PENDIENTE");

        return facturaRepository.save(factura);
    }

    // QUERY: Obtener facturas por cliente
    public List<Factura> obtenerPorCliente(String email) {
        return facturaRepository.findByClienteEmail(email);
    }

    // QUERY: Obtener todas las facturas
    public List<Factura> obtenerTodas() {
        return facturaRepository.findAll();
    }

    // COMMAND: Marcar factura como pagada
    public Factura marcarComoPagada(Long pedidoId) {
        Factura factura = facturaRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada para pedido: " + pedidoId));
        factura.setEstado("PAGADA");
        return facturaRepository.save(factura);
    }
}