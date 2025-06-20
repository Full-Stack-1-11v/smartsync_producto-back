package cl.ecomarket.producto.service;

import java.util.List;

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

    /*
     * Metodo que permite listar todos los pedidos.
     * @return Lista de objetos {@link PedidoDTO}
     */
    public List<PedidoDTO> verPedidos(){return pedidoFeign.getPedido();}

}