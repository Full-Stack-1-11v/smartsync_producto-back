package cl.ecomarket.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.producto.model.Producto;
import cl.ecomarket.producto.repository.ProductoRepository;
import jakarta.transaction.Transactional;

/*
 * Servicio que permite gestionar los productos.
 * Proporciona metodos para listar, buscar por id, guardar y eliminar.
 */
@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /*
     * Metodo que permite Listar todos los productos.
     * @return lista de objetos {@link Productos}.
     */
    public List<Producto> findAll(){return productoRepository.findAll();};

    /*
     * Metodo que permite buscar un producto por su id.
     * @param ID producto.
     * @return Objeto {@link Producto}.
     */
    public Producto findById(Long id){return productoRepository.findByIdProducto(id);};

    /*
     * Metodo que permite guardar un producto.
     * @param objeto completo {@link Producto}.
     * @return Objeto tipo {@link Producto} creado.
     */
    public Producto save(Producto producto){return productoRepository.save(producto);};

    /*
     * Metodo que buscar y elimina un producto por su ID.
     * @param ID Producto.
     */
    public void delete (Long id){productoRepository.deleteById(id);};

}
