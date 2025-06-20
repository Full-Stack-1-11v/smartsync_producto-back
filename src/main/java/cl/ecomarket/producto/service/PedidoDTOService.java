package cl.ecomarket.producto.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.producto.client.PedidoFeignClient;
import cl.ecomarket.producto.dto.PedidoDTO;

/*
 * Servicio para gestionar la API Pedido llamada desde feign client.
 * Permite listar todos los pedidos 
 */
@Service
public class PedidoDTOService {

    @Autowired
    private PedidoFeignClient pedidoFeign;

    private static final Logger logger = LoggerFactory.getLogger(PedidoDTOService.class);

    /*
     * Metodo que permite listar todos los pedidos.
     * @return Lista de objetos {@link PedidoDTO}
     */
    public List<PedidoDTO> verPedidos(){
        logger.info("[verPedidos] Inicio.");
        return pedidoFeign.getPedido();
    }

}