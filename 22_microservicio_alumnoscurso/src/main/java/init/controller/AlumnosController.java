package init.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.dtos.AlumnoDto;
import init.dtos.ItemDto;
import init.model.Alumno;
import init.service.AlumnosService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


//PATHVARIABLE PARA COSAS PEQUEÑAS, PALABRAS...
//REQUESTPARAM PARA ENVIAR COSAS MAS LARGAS, CODIGO CON CARACTERES ESPECIALES, URLS...
@Controller
public class AlumnosController {
	
	@Autowired
	AlumnosService alumnosService;
	
	@GetMapping(value="alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> consultar(){
		return new ResponseEntity<>(alumnosService.mostrarCursos(), HttpStatus.OK);
	}
	
	@GetMapping(value="alumnos/alumnos-curso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> mostrarAlumnosCurso(@PathVariable String curso) {
		return new ResponseEntity<>(alumnosService.mostrarAlumnosCurso(curso), HttpStatus.OK);
	}
	
	@PostMapping(value="alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaAlumno(@RequestBody AlumnoDto alumno) {
		//Alumno alumno = new Alumno(nombre, curso, email, nota);
		if(alumnosService.guardar(alumno)) {
			return new ResponseEntity<>( HttpStatus.CREATED);
		}
		return new ResponseEntity<>( HttpStatus.CONFLICT);
	}
	@DeleteMapping(value= "baja", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> delete (@RequestParam int idAlumno) {
		return ResponseEntity.ok(alumnosService.eliminar(idAlumno));
	}
}
