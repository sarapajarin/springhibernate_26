package init.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import init.dtos.CredentialsDto;
import init.model.Alumno;

//la interfaz feign es como un repositorio
@FeignClient(name="alumnosFeign", url ="${remote.url}")
public interface AlumnosFeign {
	@GetMapping("alumnos")
	List<Alumno> alumnos( @RequestHeader(name = "Authorization") String auth);
	@PostMapping("alumnos")
	void nuevoAlumno(@RequestBody Alumno alumno, @RequestHeader(name = "Authorization") String auth);
	@PostMapping("login")
	String autenticar(@RequestBody CredentialsDto credentials);
}
