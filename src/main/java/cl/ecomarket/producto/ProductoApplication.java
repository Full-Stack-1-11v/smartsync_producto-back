package cl.ecomarket.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/*
 * Aplicacion principal de Ecomarket Productos.
 * Permite iniciar la aplicacion y configurar los componentes necesarios.
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "cl.ecomarket.producto")
public class ProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
	}

}
