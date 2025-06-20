package cl.ecomarket.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Clase DTO para representar un pedido.
 * Contiene los campos necesarios para la transferencia de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long pedidoId;
    
    private boolean estadoPedido;

    private String fechaPedido;

}
