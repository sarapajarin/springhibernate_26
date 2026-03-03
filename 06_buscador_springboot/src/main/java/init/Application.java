package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"service","controller"})
public class Application { //donde inicia la app

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
