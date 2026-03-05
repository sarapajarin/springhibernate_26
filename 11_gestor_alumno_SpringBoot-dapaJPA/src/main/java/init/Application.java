package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication //nos podemos ahorra estan anotaciones poniendo los paquete en el mismo paquete que init
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
