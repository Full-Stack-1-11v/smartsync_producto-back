package cl.ecomarket.producto.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.producto.dto.PedidoDTO;


/*
 * Interfaz que permite la comunicacion con la API de pedidos.
 * Utiliza la dependencia Open Feign para llamar a la API pedidos.
 */
@FeignClient(name = "Pedido-Api", url = "https://smartsync-pedido-back-2-0.onrender.com")
public interface PedidoFeignClient {

    @GetMapping("/api/v1/pedidos/listar")
    List<PedidoDTO> getPedido();    

}
