package cl.ecomarket.producto.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.ecomarket.producto.controller.ProductoControllerV2;
import cl.ecomarket.producto.dto.PedidoDTO;

@Component
public class PedidoDTOModelAssembler implements RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>>{

    @Override
    public EntityModel<PedidoDTO> toModel(PedidoDTO pedidoDTO){
        Link selfLink = linkTo(methodOn(ProductoControllerV2.class).listarPedido())
                            .withSelfRel();        
        return EntityModel.of(pedidoDTO, selfLink);
    }

}
