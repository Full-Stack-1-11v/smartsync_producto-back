package cl.ecomarket.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.producto.client.PedidoFeignClient;
import cl.ecomarket.producto.dto.PedidoDTO;

@Service
public class PedidoDTOService {

    @Autowired
    private PedidoFeignClient pedidoFeign;

    public List<PedidoDTO> verPedidos(){return pedidoFeign.getPedido();}

}