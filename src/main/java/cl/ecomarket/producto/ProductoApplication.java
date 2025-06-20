package cl.ecomarket.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/*
 * Clase main de la aplicacion.
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "cl.ecomarket.producto")
public class ProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
	}

}
