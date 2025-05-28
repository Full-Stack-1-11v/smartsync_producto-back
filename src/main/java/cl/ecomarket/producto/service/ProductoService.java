package cl.ecomarket.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.producto.model.Producto;
import cl.ecomarket.producto.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){return productoRepository.findAll();};

    public Producto findById(Long id){return productoRepository.findByIdProducto(id);};

    public Producto save(Producto producto){return productoRepository.save(producto);};

    public void delete (Long id){productoRepository.deleteById(id);};

}
