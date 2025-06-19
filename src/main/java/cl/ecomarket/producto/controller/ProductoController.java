package cl.ecomarket.producto.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import cl.ecomarket.producto.dto.PedidoDTO;
import cl.ecomarket.producto.model.Producto;
import cl.ecomarket.producto.service.PedidoDTOService;
import cl.ecomarket.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/*
 * Controlador de tipo REST para gestionar los productos. 
 * Proporciona endpoints del tipo listar productos,buscar por id de producto,
 * guardar producto, actualizar producto y borrar producto.
 */

@RestController
@RequestMapping("/api/v1/ecomarket/producto")
@Tag(name = "Productos.", description = "Operaciones relacionadas a los productos.")
public class ProductoController {

    /* 
     * Service para gestionar productos.
     */
    @Autowired
    private ProductoService productoService;

    /*
     * Service para gestionar pedidos.
     */

    @Autowired
    private PedidoDTOService pDTOService;

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("/pedidos")
    @Operation(summary = "Obtener pedidos.", description = "Obtiene una lista de todos los pedidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos listados.",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class))),
                @ApiResponse(responseCode = "204", description = "Pedidos vacios.")})
    public ResponseEntity<List<PedidoDTO>> listarPedido(){
        /* 
         * Logger inicial del metodo listar Pedidos.
         */
        logger.info("[listarPedidos] Inicio.");
        List<PedidoDTO> pedidos = pDTOService.verPedidos();
        if(pedidos.isEmpty()){
            /*
             * Logger que advierte cuando no se encuentran pedidos.
             */
            logger.warn("No se encontraron Pedidos.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos.", description = "Obtiene una lista de todos los productos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos listados.",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
                @ApiResponse(responseCode = "204", description = "Productos vacios.")})
    public ResponseEntity<List<Producto>> listar(){
        logger.info("[listar] Inicio.");
        List<Producto> productos = productoService.findAll();
        if(productos.isEmpty()){
            logger.warn("No se encontraron productos.");
            return ResponseEntity.noContent().build();
        }
        logger.info("Productos listados.");
        return ResponseEntity.ok(productos);
    }
    

    @GetMapping("/{id}/buscar")
    @Operation(summary = "Obtener producto por su ID", description = "Buscar y obtiene un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
                @ApiResponse(responseCode = "404", description = "Producto no encontrado.")})
    public ResponseEntity<Producto> buscar(@PathVariable Long id){
        logger.info("[buscar] Inicio .");
        logger.debug("[buscar] Buscar un producto por su id: {} .", id);
        try {
            Producto producto = productoService.findById(id);
            logger.info("[buscar] Se encontro el producto: {} con el ID: {} .",producto.getNombreProducto(),id);
            logger.info("[buscar] Fin.");
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            logger.warn("No se encontraron productos con el ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un producto nuevo.", description = "Guarda un producto nuevo en la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto nuevo guardado exitosamente.",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class)))})
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
        logger.info("[guardar] Inicio.");
        Producto nuevoProducto = productoService.save(producto);
        logger.info("Producto nuevo guardado.");
        logger.info("[guardar] Fin.");
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}/actualizar")
    @Operation(summary = "Actualizar un producto", description = "Actualiza un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
                @ApiResponse(responseCode = "404", description = "Producto no encontrado")})
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        logger.info("[actualizar] Inicio.");
        logger.debug("[actualizar] Actualizando producto con la ID: {}",id);
        try {
            Producto pro = productoService.findById(id);
            pro.setIdProducto(id);
            pro.setNombreProducto(producto.getNombreProducto());
            pro.setPrecioProducto(producto.getPrecioProducto());
            pro.setStockProducto(producto.getStockProducto());
            productoService.save(pro);
            logger.info("Producto con ID: {}, Actualizado",id);
            logger.info("[actualizar] Fin.");
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            logger.error("Error: ", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")})
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        logger.info("[eliminar] Inicio.");
        try {
            productoService.delete(id);
            logger.info("Producto con ID: {}, eliminado",id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.warn("Error: {}", e);
            return ResponseEntity.noContent().build();  
        }
    }

}
