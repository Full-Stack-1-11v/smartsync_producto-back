package cl.ecomarket.producto.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.producto.dto.PedidoDTO;

@FeignClient(name = "Pedido-Api", url = "https://smartsync-pedido-back-2-0.onrender.com")
public interface PedidoFeignClient {

    @GetMapping("/api/v1/pedidos/listar")
    List<PedidoDTO> getPedido();    

}
