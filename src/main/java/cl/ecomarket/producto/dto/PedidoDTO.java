package cl.ecomarket.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para representar un pedido.
 * Contiene los campos necesarios para la transferencia de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    /**
     * Id de pedido
     */
    private Long pedidoId;
    
    /**
     * Estado del pedido
     */
    private boolean estadoPedido;

    /**
     * Fecha del pedido
     */
    private String fechaPedido;

}
