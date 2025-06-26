package cl.ecomarket.producto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import cl.ecomarket.producto.model.Producto;
import cl.ecomarket.producto.repository.ProductoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testGetProductos(){

        //GIVEN

        List<Producto> productos = new ArrayList<>();

        //WHEN
        when(productoRepository.findAll()).thenReturn(productos);

        //THEN
        List<Producto> result = productoService.findAll();


        assertEquals(productos, result);
        assertEquals(0,result.size());
        
    }

    @Test
    public void testGetProductoById(){
        // Given
        Long idProducto = 1L;
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        
        // When
        when(productoRepository.findByIdProducto(idProducto)).thenReturn(producto);
         
        // Then
        Producto resultado = productoService.findById(idProducto);
        assertEquals(producto, resultado);               
}
    @Test
    public void testSaveProducto(){
        //Given
        Long idProdu = 1L;
        String nomProdu = "A";
        int precioProdu = 10;
        int stockProdu = 10;
        Producto produSave = new Producto();
        produSave.setIdProducto(idProdu);
        produSave.setNombreProducto(nomProdu);
        produSave.setPrecioProducto(precioProdu);
        produSave.setStockProducto(stockProdu);

        //When
        when(productoRepository.save(produSave)).thenReturn(produSave);

        //Then
        Producto resultadoSave = productoService.save(produSave);
        assertEquals(produSave, resultadoSave);

    }

    @Test
    public void testDeleteProducto(){
        // Given
        Long idDelete = 1L;
        
        // When
        productoService.delete(idDelete);
        
        // Then
        verify(productoRepository, times(1)).deleteById(idDelete);
}

}
