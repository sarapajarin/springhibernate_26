package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.AlumnoDto;
import init.service.AlumnosService;

@RestController
public class AlumnosController {
	
	@Autowired
	AlumnosService alumnosService;
	
	@GetMapping("alumnos/cursos")
	public ResponseEntity<List<String>> getCursos() {
		if(alumnosService.getAllCursos()== null ) {
			return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
		}
		return ResponseEntity.ok(alumnosService.getAllCursos());
	}
	
	
	@GetMapping("alumnos")
	public ResponseEntity<List<AlumnoDto>> getAlumnos() {
		if(alumnosService.getAllAlumnos()== null ) {
			return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
		}
		return ResponseEntity.ok(alumnosService.getAllAlumnos());
	}
	
	@GetMapping("alumnos/por-curso")
	public ResponseEntity<List<AlumnoDto>> getAlumnos(@RequestParam String curso) {
		if(alumnosService.alumnosByCurso(curso)== null ) {
			return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
		}
		return ResponseEntity.ok(alumnosService.alumnosByCurso(curso));
	}
	
	@PostMapping("alumnos")
	public ResponseEntity<AlumnoDto> crearAlumno(@RequestBody AlumnoDto alumno) {
		if(!alumnosService.guardar(alumno)) {
			return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@DeleteMapping("alumnos")
	public ResponseEntity<AlumnoDto> crearAlumno(@RequestParam String email) {
		AlumnoDto al = alumnosService.findByEmail(email);
		if(al!=null) {
			return ResponseEntity.ok(alumnosService.deleteAlumnoByEmail(email));
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
}
	}