package cl.ecomarket.producto.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoDTOTest {

    @Test
    public void testPedidoDTOGetterAndSetters(){
        PedidoDTO pedidoDto = new PedidoDTO();
        pedidoDto.setPedidoId(1L);
        pedidoDto.setEstadoPedido(true);
        pedidoDto.setFechaPedido("20/05/2024");

        assertEquals(1L, pedidoDto.getPedidoId());
        assertEquals("20/05/2024", pedidoDto.getFechaPedido());
        assertEquals(true, pedidoDto.isEstadoPedido());
    }

    @Test
    public void testEqualsAndHashCode(){
        PedidoDTO pedido1 = new PedidoDTO(1L, true, "20/05/2024");
        PedidoDTO pedido2 = new PedidoDTO(1L, true, "20/05/2024");
        PedidoDTO pedido3 = new PedidoDTO(3L, false, "10/06/2024");
        PedidoDTO pedido4 = new PedidoDTO(null, false, null);
        PedidoDTO pedido5 = new PedidoDTO(null, false, null);
        PedidoDTO pedidoVacio1 = new PedidoDTO();
        PedidoDTO pedidoVacio2 = new PedidoDTO();

        //Pedido igual a si mismo
        assertEquals(pedido1, pedido1);

        //Pedido igual a otro y diferente a otro
        assertEquals(pedido1, pedido2);
        assertNotEquals(pedido1, pedido3);

        //Igual con hashcode
        assertEquals(pedido1.hashCode(), pedido2.hashCode());
        assertNotEquals(pedido1.hashCode(), pedido3.hashCode());

        //Distinto a valor nulo
        assertNotEquals(pedido1, null);

        //Datos distintos
        assertNotEquals(pedido1, "Pedido");
        assertNotEquals(pedido1.hashCode(), Integer.valueOf(20));

        //Objetos con campos nulos
        assertEquals(pedido4, pedido5);
        assertEquals(pedido4.hashCode(), pedido5.hashCode());
        assertNotEquals(pedido4.hashCode(), pedido3.hashCode());

        //Objetos vacios con mismo hashCode
        assertEquals(pedidoVacio1.hashCode(), pedidoVacio2.hashCode());
        assertNotEquals(pedidoVacio1.hashCode(), pedido1.hashCode());
    }

    @Test
    public void testToString(){
        PedidoDTO pedido = new PedidoDTO(1L, true, "20/10/2020");
        String str = pedido.toString();
        assertNotNull(str);
        assertTrue(str.contains("1"));        
        assertTrue(str.contains("true"));
        assertTrue(str.contains("20/10/2020"));
    }

    @Test
    public void testEqualsWithNullFields(){
        //Objeto con fecha null
        PedidoDTO pedido1 = new PedidoDTO(1L, false, null);
        PedidoDTO pedido2 = new PedidoDTO(1L, false, "20/10/2020");
        assertFalse(pedido1.equals(pedido2));
        assertFalse(pedido2.equals(pedido1));

        //Objetos con fecha null
        PedidoDTO pedido3 = new PedidoDTO(1L, false, null);
        assertTrue(pedido1.equals(pedido3));

    }

    @Test
    public void testEqualWithDifferentValues(){
        PedidoDTO pedido01 = new PedidoDTO(1L, false, "20/10/2021");
        PedidoDTO pedido02 = new PedidoDTO(1L, false, "20/10/2022");
        //Pedidos con diferente fecha
        assertFalse(pedido01.equals(pedido02));
        assertFalse(pedido02.equals(pedido01));

        //Pedidos con diferente estado
        PedidoDTO pedido03 = new PedidoDTO(1L, false, "20/10/2022");
        PedidoDTO pedido04 = new PedidoDTO(1L, true, "20/10/2022");
        assertFalse(pedido03.equals(pedido04));
        assertFalse(pedido04.equals(pedido03));

        //Pedidos con diferente ID
        PedidoDTO pedido05 = new PedidoDTO(1L, false, "20/10/2020");
        PedidoDTO pedido06 = new PedidoDTO(2L, false, "20/10/2020");
        assertFalse(pedido05.equals(pedido06));
        assertFalse(pedido06.equals(pedido05));

        //Pedido de prueba con mismos valores retornando true
        PedidoDTO pedido07 = new PedidoDTO(1L, false, "20/20/2002");    
        PedidoDTO pedido08 = new PedidoDTO(1L, false, "20/20/2002");
        assertTrue(pedido07.equals(pedido08));
        assertTrue(pedido08.equals(pedido07));
    }
    
    @Test
    public void testEqualsWithNull(){
        PedidoDTO pedido = new PedidoDTO(1L, false, "20/20/2020");
        assertFalse(pedido.equals(null));
    }

    @Test
    public void testWithDifferentClass(){
        PedidoDTO pedido = new PedidoDTO(5L, true, "01/04/2024");
        assertFalse(pedido.equals("No es un pedido"));
    }    

}
