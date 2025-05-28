package cl.ecomarket.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ecomarket.producto.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    @Query("SELECT p FROM Producto p WHERE p.nombreProducto = :nombre")
    List<Producto> findByNombre(String nombre);

    public Producto findByIdProducto(Long id);

    public Producto findByNombreProducto(String nombre);

    //Query nativa
    @Query(value = "SELECT * FROM Producto WHERE nombre = :nombre", nativeQuery = true)
    List<Producto> findByNombreNativo(String nombre);
}
