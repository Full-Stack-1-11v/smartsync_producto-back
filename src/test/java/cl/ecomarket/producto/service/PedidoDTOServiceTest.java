package cl.ecomarket.producto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import cl.ecomarket.producto.client.PedidoFeignClient;
import cl.ecomarket.producto.dto.PedidoDTO;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoDTOServiceTest {

    @Autowired
    private PedidoDTOService pedidoService;

    @MockBean
    private PedidoFeignClient pedidoFeignClient;    

    @Test
    public void testVerPedidos(){
        //Given
        List<PedidoDTO> pedidos = new ArrayList<>();

        //When
        when(pedidoFeignClient.getPedido()).thenReturn(pedidos);

        //Then
        List<PedidoDTO> result = pedidoService.verPedidos();

        assertEquals(pedidos, result);
        assertEquals(0, result.size());
    }
}
