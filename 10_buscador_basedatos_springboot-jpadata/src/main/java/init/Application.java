package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"service", "controller"})//no hace falta model pq o tiene service .. controller
@EnableJpaRepositories(basePackages="respository") //ANOTACION SCAN DE ARRIBA SOLO VALE PARA CLASES, NO INTERFACES, POR ESO ESTA ANOTACION
@EntityScan(basePackages = "model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
