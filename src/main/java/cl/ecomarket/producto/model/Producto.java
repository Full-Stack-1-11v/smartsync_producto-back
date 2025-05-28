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

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "Nombre_producto", nullable = false)
    private String nombreProducto;
    
    @Column(name = "Precio_producto", nullable = false)
    private int precioProducto;

    @Column(name = "Stock_producto", nullable = false)
    private int stockProducto;

}
