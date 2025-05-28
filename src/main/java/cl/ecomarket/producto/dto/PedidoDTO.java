package cl.ecomarket.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long pedidoId;
    
    private boolean estadoPedido;

    private String fechaPedido;

}
