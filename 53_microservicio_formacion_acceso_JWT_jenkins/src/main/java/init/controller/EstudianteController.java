package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.EstudianteDto;
import init.service.EstudianteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class EstudianteController {
	
	@Autowired
	EstudianteService estudiantesService;
	
	@GetMapping("estudiantes")
	public ResponseEntity<List<EstudianteDto>> estudiantesCalificacion(double min, double max){
		return ResponseEntity.ok(estudiantesService.estudiantesRangoCalificaciones(min, max));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode="201", description = "El estudiante se ha añadido correctamente"),
		@ApiResponse(responseCode="409", description = "No se ha creado el estudiante porque no se puede repetir")
	})

	
	@PostMapping("estudiantes")
	public ResponseEntity<Void> altaEstudiante(@RequestBody EstudianteDto estudiante){
		if(estudiantesService.alta(estudiante)) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}
