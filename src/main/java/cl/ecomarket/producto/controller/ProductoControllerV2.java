package cl.ecomarket.producto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /**
     * Controlador de tipo REST para gestionar los productos V2.
     * Esta version tiene implementado HATEOAS y LOGGER. 
     * Proporciona endpoints del tipo listar productos,buscar por id de producto,
     * guardar producto, actualizar producto y borrar producto.
     */

    /**
     * Service para gestionar productos.
     */
    @Autowired
    private ProductoService productoService;

    /**
     * Service para gestionar pedidos.
     */
    @Autowired
    private PedidoDTOService pDTOService;

    /**
     * Logger de la clase para registrar eventos o errores.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProductoControllerV2.class);

     /**
      * Assembler para implementar HATEOAS a los metodos REST de Producto.
      */
    @Autowired
    private ProductoModelAssembler assembler;

    /**
     * Assembler para implementar HATEOAS al metodo REST GET de Pedidos.
     */
    @Autowired
    private PedidoDTOModelAssembler assemblerDTO;

    /**
     * Metodo Rest del tipo GET.
     * Llama a la API pedidos y obtiene una lista de todos los pedidos.
     * @return lista de objetos {@link PedidoDTO}
     */
    @GetMapping("/pedidos")
    public ResponseEntity<List<EntityModel<PedidoDTO>>> listarPedido(){
        logger.info("[listarPedido] Inicio.");
        List<EntityModel<PedidoDTO>> pedidos = pDTOService.verPedidos().stream().map(assemblerDTO::toModel)
                                                                .collect(Collectors.toList());
        if(pedidos.isEmpty()){
            logger.warn("No se encontraron pedidos");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

   /**
    * Metodo Rest del tipo GET.
    * Obtiene una lista de todos los productos de la API.
    * @return lista de objetos {@link Producto}
    */
    @GetMapping
    public ResponseEntity<List<EntityModel<Producto>>> listar(){
        logger.info("[listar] Inicio.");
        List<EntityModel<Producto>> productos = productoService.findAll().stream().map(assembler::toModel)
                                                                .collect(Collectors.toList());
        if(productos.isEmpty()){
            logger.warn("No se encontraron productos");
            return ResponseEntity.noContent().build();
        }
        logger.info("Productos listados.");
        return ResponseEntity.ok(productos);
    }    

    /**
     * Metodo Rest del tipo GET.
     * Buscar un producto por su ID y retorna sus atributos.
     * @param id producto.
     * @return Objeto del tipo {@link Producto}.
     */
    @GetMapping("/{id}/buscar")   
    public ResponseEntity<EntityModel<Producto>> buscar(@PathVariable Long id){
        logger.info("[buscar] Inicio.");
        logger.debug("[buscar] Busca un producto por su id: {}.",id);
        try {
            Producto producto = productoService.findById(id);
            EntityModel<Producto> productoModel = assembler.toModel(producto);
            logger.info("Se encontro el producto: {}, con el ID: {}.",producto.getNombreProducto(),id);
            logger.info("[buscar] Fin.", productoModel);
            return ResponseEntity.ok(productoModel);
        } catch (Exception e) {
            logger.warn("No se encontradon productos con el ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Metodo Rest del tipo POST.
     * Crea un objeto y lo guarda en la base de datos.
     * @param producto completo del Producto {@link Producto}.
     * @return Objeto tipo {@link Producto} Creado.
     */
    @PostMapping("/guardar")
    public ResponseEntity<EntityModel<Producto>> guardar(@RequestBody Producto producto){
        logger.info("[guardar] Inicio.");
        Producto nuevoProducto = productoService.save(producto);        
        logger.info("Producto nuevo guardado.");
        logger.info("[guardar] Fin.");
        EntityModel<Producto> productoModel = assembler.toModel(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoModel);
    }

    /**
     * Metodo Rest del tipo PUT.
     * Busca un Producto por su ID y lo actualiza a travez de su cuerpo.
     * @param id Producto.
     * @return Objeto tipo {@link Producto} Actualizado.
     */
    @PutMapping("/{id}/actualizar")    
    public ResponseEntity<EntityModel<Producto>> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        logger.info("[actualizar] Inicio.");
        logger.debug("[actualizar] Actualizando producto con la ID: {}",id);
        try {
            Producto pro = productoService.findById(id);
            pro.setIdProducto(id);
            pro.setNombreProducto(producto.getNombreProducto());
            pro.setPrecioProducto(producto.getPrecioProducto());
            pro.setStockProducto(producto.getStockProducto());
            productoService.save(pro);
            logger.info("Producto con ID: {}, actualizado",id);
            logger.info("[actualizar] Fin.");
            EntityModel<Producto> productoModel = assembler.toModel(pro);
            return ResponseEntity.ok(productoModel);
        } catch (Exception e) {
            logger.error("Error: {}", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Metodo Rest del tipo DELETE.
     * Busca un producto por su ID y lo elimina.
     * @param id Producto.
     */
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        logger.info("[eliminar] Inicio.");
        try {
            productoService.delete(id);
            logger.info("Producto con ID: {}, eliminado",id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error: {}", e);
            return ResponseEntity.noContent().build();
        }
    }

}
