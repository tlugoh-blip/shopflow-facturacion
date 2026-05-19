package co.shopflow.facturacion.listener;

import co.shopflow.facturacion.config.RabbitMQConfig;
import co.shopflow.facturacion.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PedidoListener {

    private final FacturaService facturaService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_FACTURACION)
    public void recibirPedido(Map<String, Object> pedido) {
        System.out.println("📨 Facturación recibió pedido: " + pedido.get("id"));
        facturaService.generarFactura(pedido);
        System.out.println("✅ Factura generada para pedido: " + pedido.get("id"));
    }
}