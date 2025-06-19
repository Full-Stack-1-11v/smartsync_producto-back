package cl.ecomarket.producto.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cl.ecomarket.producto.dto.PedidoDTO;
import cl.ecomarket.producto.model.Producto;
import cl.ecomarket.producto.service.PedidoDTOService;
import cl.ecomarket.producto.service.ProductoService;

@WebMvcTest(ProductoControllerV2.class)
public class ProductoControllersV2Test {

    @MockBean
    private ProductoService productoService;

    @MockBean
    private PedidoDTOService pedidoDTOService;

    @Autowired  
    private MockMvc mockMvc;

    @MockBean
    private RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>> assemblerDTO;

    @Test
    @DisplayName("GET api/v2/ecomarket/producto Devuelve un 200(ok) si encuentra datos")
    void findProductosTest() throws Exception{
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        when(productoService.findAll()).thenReturn(Arrays.asList(producto1,producto2));

        mockMvc.perform(get("/api/v2/ecomarket/producto")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("GET /api/v2/ecomarket/producto Devuelve un 204(NotContent) si no hay contenido") 
    void findProductosEmptyTest () throws Exception{
        when(productoService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v2/ecomarket/producto")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/v2/ecomarket/producto/{id}/buscar Busca un producto y devuelve un 200(Ok) si lo encuentra")
    void findProductoByIdTest() throws Exception{
        Producto producto = new Producto(1L, "papa", 1000, 10);
        when(productoService.findById(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/v2/ecomarket/producto/1/buscar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1L))
                .andExpect(jsonPath("$.nombreProducto").value("papa"))
                .andExpect(jsonPath("$.precioProducto").value(1000))
                .andExpect(jsonPath("$.stockProducto").value(10));
    }

    @Test
    @DisplayName("GET /api/v2/ecomarket/producto/{id}/buscar buscar un producto y devuelve un 204(Not Found)")
    void findProductoByIdEmptyTest() throws Exception{
        when(productoService.findById(1L)).thenThrow(new RuntimeException("Producto no encontrado"));

        mockMvc.perform(get("/api/v2/ecomarket/producto/1/buscar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v2/ecomarket/producto/guardar Guarda un producto y devuelve un 201(Created)")
    void saveProductosTest() throws Exception {
        Producto producto = new Producto(1L, "papa", 1000, 10);
        when(productoService.save(producto)).thenReturn(producto);

        mockMvc.perform(post("/api/v2/ecomarket/producto/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idProducto\":1,\"nombreProducto\":\"papa\",\"precioProducto\":1000,\"stockProducto\":10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idProducto").value(1L))
                .andExpect(jsonPath("$.nombreProducto").value("papa"))
                .andExpect(jsonPath("$.precioProducto").value(1000))
                .andExpect(jsonPath("$.stockProducto").value(10));
    }

    @Test
    @DisplayName("PUT /api/v2/ecomarket/producto/{id}/actualizar Actualiza un producto y devuelve un 200(ok)")
    void updateProductoByIdTest() throws Exception{
        when(productoService.findById(1L)).thenReturn(new Producto(1L, "papa", 1000, 10));
        Producto updatedProducto = new Producto(1L, "papa actualizada", 1200, 15);
        when(productoService.save(updatedProducto)).thenReturn(updatedProducto);

        mockMvc.perform(put("/api/v2/ecomarket/producto/1/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idProducto\":1,\"nombreProducto\":\"papa actualizada\",\"precioProducto\":1200,\"stockProducto\":15}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1L))
                .andExpect(jsonPath("$.nombreProducto").value("papa actualizada"))
                .andExpect(jsonPath("$.precioProducto").value(1200))
                .andExpect(jsonPath("$.stockProducto").value(15));  
    }

    @Test
    @DisplayName("PUT /api/v2/ecomarket/producto/{id}/actualizar Devuelve un 204 al no encontrar el producto")
    void updateProductoByIdEmptyTest() throws Exception{
        when(productoService.findById(1L)).thenThrow(new RuntimeException("Producto no encontrado"));

        mockMvc.perform(put("/api/v2/ecomarket/producto/1/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idProducto\":1,\"nombreProducto\":\"papa actualizada\",\"precioProducto\":1200,\"stockProducto\":15}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v2/ecomarket/producto/1/eliminar Elimina un producto y devuelve un 204(NoContent)")
    void deleteProductoByIdTest() throws Exception{
        mockMvc.perform(delete("/api/v2/ecomarket/producto/1/eliminar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    
    @Test
    @DisplayName("DELETE /api/v2/ecomarket/producto/1/eliminar Devuelve un 404 si no existe el producto")
    void deleteProductoByIdEmptyTest() throws Exception{
        doThrow(new RuntimeException("No encontrado")).when(productoService).delete(1L);

        mockMvc.perform(delete("/api/v2/ecomarket/producto/1/eliminar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    

}
