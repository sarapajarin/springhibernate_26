package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Alumno;
import service.AlumnosService;

@Controller
public class AlumnosController {
	
	@Autowired
	AlumnosService alumnosService;
	
	@GetMapping("consulta")
	public String consultar(Model model){
		model.addAttribute("cursos", alumnosService.mostrarCursos());
		return "consulta";
	}
	
	@GetMapping("alumnos")
	public String mostrarAlumnosCurso(Model model, @RequestParam("curso")String curso) {
		model.addAttribute("alumnosCurso", alumnosService.mostrarAlumnosCurso(curso));
		return "consulta";
	}
	
	@PostMapping("alta")
	public String altaAlumno(Model model, 
			@RequestParam("nombre")String nombre, 
			@RequestParam("curso")String curso, 
			@RequestParam("email")String email, 
			@RequestParam("nota")float nota) {
		Alumno alumno = new Alumno(nombre, curso, email, nota);
		if(alumnosService.guardar(alumno)) {
			model.addAttribute("mensaje", "Alumno Guardado");
			return "mensaje";
		}else {
			model.addAttribute("mensaje", "Error:Alumno ya guardado");
			return "mensaje";
		}
		
	}
	@GetMapping({"goMenu", "/"})
	public String menu() {
		return "menu";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
		model.addAttribute("cursos", alumnosService.mostrarCursos());
		return "nuevo";
	}
	@GetMapping("mensaje")
	public String mensaje() {
		return "mensaje";
	}
}
