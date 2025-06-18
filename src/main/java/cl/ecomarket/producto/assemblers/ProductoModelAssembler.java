package cl.ecomarket.producto.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.ecomarket.producto.controller.ProductoControllerV2;
import cl.ecomarket.producto.model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

    @Override
    public EntityModel<Producto> toModel(Producto producto){
        Link selfLink = linkTo(methodOn(ProductoControllerV2.class).buscar(producto.getIdProducto()))
                                .withSelfRel();
        
        Link allProductos = linkTo(methodOn(ProductoControllerV2.class).listar()).withRel("productos");

        Link crearLink = linkTo(methodOn(ProductoControllerV2.class).actualizar(producto.getIdProducto(), producto))
                        .withRel("crear")
                        .withType("Post");
        
        return EntityModel.of(producto, selfLink,allProductos,crearLink);
    }

}
