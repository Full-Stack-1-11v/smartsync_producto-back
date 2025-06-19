package cl.ecomarket.producto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.producto.assemblers.PedidoDTOModelAssembler;
import cl.ecomarket.producto.assemblers.ProductoModelAssembler;
import cl.ecomarket.producto.dto.PedidoDTO;
import cl.ecomarket.producto.model.Producto;
import cl.ecomarket.producto.service.PedidoDTOService;
import cl.ecomarket.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/ecomarket/producto")
@Tag(name = "Productos", description = "Operaciones relacionadas a los productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoDTOService pDTOService;

    @Autowired
    private ProductoModelAssembler assembler;

    private PedidoDTOModelAssembler assemblerDTO;

    @GetMapping("/pedidos")
    public ResponseEntity<List<EntityModel<PedidoDTO>>> listarPedido(){
        List<EntityModel<PedidoDTO>> pedidos = pDTOService.verPedidos().stream().map(assemblerDTO::toModel)
                                                                .collect(Collectors.toList());
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Producto>>> listar(){
        List<EntityModel<Producto>> productos = productoService.findAll().stream().map(assembler::toModel)
                                                                .collect(Collectors.toList());
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }    

    @GetMapping("/{id}/buscar")   
    public ResponseEntity<EntityModel<Producto>> buscar(@PathVariable Long id){
        try {
            Producto producto = productoService.findById(id);
            EntityModel<Producto> productoModel = assembler.toModel(producto);
            return ResponseEntity.ok(productoModel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<EntityModel<Producto>> guardar(@RequestBody Producto producto){
        Producto nuevoProducto = productoService.save(producto);
        EntityModel<Producto> productoModel = assembler.toModel(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoModel);
    }

    @PutMapping("/{id}/actualizar")    
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        try {
            Producto pro = productoService.findById(id);
            pro.setIdProducto(id);
            pro.setNombreProducto(producto.getNombreProducto());
            pro.setPrecioProducto(producto.getPrecioProducto());
            pro.setStockProducto(producto.getStockProducto());
            productoService.save(pro);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

}
