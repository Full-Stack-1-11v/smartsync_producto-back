package cl.ecomarket.producto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/*
 * Clase de configuracion basica de Swagger.
 * Permite modificar el titulo, la version y dar una 
 * descripcion
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                    .info(new Info()
                            .title("Api Productos")
                            .version("1.0")
                            .description("Documentacion API EcoMarket Productos"));
    }

}
