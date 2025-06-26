package cl.ecomarket.producto.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProductoTest {

    @Test
    public void testProductoGettersAndSetters() {
        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setNombreProducto("Manzana");
        producto.setPrecioProducto(1000);
        producto.setStockProducto(50);

        assertEquals(1L, producto.getIdProducto());
        assertEquals("Manzana", producto.getNombreProducto());
        assertEquals(1000, producto.getPrecioProducto());
        assertEquals(50,producto.getStockProducto());
              
    }

    @Test    
    public void testEqualsAndHashCode(){        
        Producto produ1 = new Producto(1L, "Producto 1", 1500, 10);
        Producto produ2 = new Producto(1L, "Producto 1", 1500, 10);        
        Producto produ3 = new Producto(3L, "Producto 3", 4100, 20);
        Producto produ4 = new Producto(null, null, 0, 0);
        Producto produ5 = new Producto(null, null, 0, 0);
        Producto productoVacio1 = new Producto();
        Producto productoVacio2 = new Producto();
 
        //Objeto igual a si mismo
        assertEquals(produ1,produ1);

        //Objetos iguales y objetos diferentes
        assertEquals(produ1,produ2);
        assertNotEquals(produ1, produ3);

        // Igual o diferente a otro objeto HashCode
        assertEquals(produ1.hashCode(), produ2.hashCode());    
        assertNotEquals(produ1.hashCode(), produ3.hashCode());            

        //Distinto campo nulo
        assertNotEquals(produ1, null);        

        // Datos distintos
        assertNotEquals(produ1, "Producto");   
        assertNotEquals(produ1, Integer.valueOf(123));    
        
        //Objetos con campos nulos
        assertEquals(produ4, produ5);
        assertEquals(produ4.hashCode(), produ5.hashCode());
        assertNotEquals(produ4.hashCode(), produ3.hashCode());

        //Objetos vacios con mismo hashCode
        assertEquals(productoVacio1, productoVacio2);
        assertNotEquals(productoVacio1.hashCode(), produ1.hashCode());
    }  

    @Test
    public void testToString() {
        Producto producto = new Producto(1L, "Manzana", 1000, 50);
        String str = producto.toString();
        assertNotNull(str);
        assertTrue(str.contains("Manzana"));
        assertTrue(str.contains("1000"));
        assertTrue(str.contains("50"));       
        
    }

    @Test
    public void testEqualsWithNullFields() {
        //Objeto con nombre y otro null
        Producto pro1 = new Producto(1L, null, 1000, 10);
        Producto pro2 = new Producto(1L, "Producto", 1000, 10);
        assertFalse(pro1.equals(pro2));
        assertFalse(pro2.equals(pro1));

        //Objetos con nombre null
        Producto pro3 = new Producto(1L, null, 1000, 10);
        Producto pro4 = new Producto(1L, null, 1000, 10);
        assertTrue(pro3.equals(pro4));

        //Objetos uno con id y otro null
        Producto pro5 = new Producto(null, "Producto", 1000, 10);
        Producto pro6 = new Producto(1L, "Producto", 1000, 10);
        assertFalse(pro5.equals(pro6));
        assertFalse(pro6.equals(pro5));

        //Ambos con id null
        Producto pro7 = new Producto(null, "Producto", 1000, 10);
        Producto pro8 = new Producto(null, "Producto", 1000, 10);
        assertTrue(pro7.equals(pro8));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Producto producto = new Producto(1L, "Manzana", 1000, 50);        
        assertFalse(producto.equals("No es producto"));
    }


    @Test
    public void testEqualsWithDifferentPrecioProducto() {
        Producto pr1 = new Producto(1L, "Producto", 1000, 10);
        Producto pr2 = new Producto(1L, "Producto", 2000, 10);
        assertFalse(pr1.equals(pr2));
        assertFalse(pr2.equals(pr1));
    }

    @Test
    public void testEqualsWithDifferentStockProducto() {
        Producto prot1 = new Producto(1L, "Producto", 1000, 10);
        Producto prot2 = new Producto(1L, "Producto", 1000, 20);
        assertFalse(prot1.equals(prot2));
        assertFalse(prot2.equals(prot1));
    }

    @Test
    public void testEqualsWithNull() {
        Producto producto = new Producto(1L, "Manzana", 1000, 50);
        assertFalse(producto.equals(null));
    }
}
