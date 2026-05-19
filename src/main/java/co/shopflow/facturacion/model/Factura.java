package co.shopflow.facturacion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    private String clienteNombre;
    private String clienteEmail;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String estado; // PENDIENTE, PAGADA
    private LocalDateTime fechaEmision = LocalDateTime.now();
}