package cl.ecomarket.producto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad que representa los Productos del sistema.
 * Contiene informacion de los productos tales como,
 * ID Producto, Nombre del producto, Precio del producto y
 * Stock del producto.
 */
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    /**
     * ID unico del productom,
     * es generado automaticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    /**
     * Nombre del producto.
     * Este campo es obligatorio
     */
    @Column(name = "Nombre_producto", nullable = false)
    private String nombreProducto;
    
    /**
     * Precio del producto
     * Este campo es obligatorio.
     */
    @Column(name = "Precio_producto", nullable = false)
    private int precioProducto;

    /**
     * Stock del producto.
     * Este campo es obligatorio.
     */
    @Column(name = "Stock_producto", nullable = false)
    private int stockProducto;

}
