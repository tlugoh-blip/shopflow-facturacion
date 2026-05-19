package co.shopflow.facturacion.controller;

import co.shopflow.facturacion.model.Factura;
import co.shopflow.facturacion.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    // QUERY: Obtener todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> obtenerTodas() {
        return ResponseEntity.ok(facturaService.obtenerTodas());
    }

    // QUERY: Obtener facturas por cliente
    @GetMapping("/cliente/{email}")
    public ResponseEntity<List<Factura>> obtenerPorCliente(@PathVariable String email) {
        return ResponseEntity.ok(facturaService.obtenerPorCliente(email));
    }

    // COMMAND: Marcar factura como pagada
    @PutMapping("/pagar/{pedidoId}")
    public ResponseEntity<Factura> marcarComoPagada(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(facturaService.marcarComoPagada(pedidoId));
    }
}