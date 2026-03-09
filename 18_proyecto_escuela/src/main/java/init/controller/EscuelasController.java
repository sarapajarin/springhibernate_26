package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.service.EscuelaService;

@Controller
public class EscuelasController {
	
	@Autowired
	EscuelaService escuelaService;
	
	@GetMapping("/") //para que arraque y matriculas ya tenga la lista de cursos
	public String obtenerCursos(Model model) {
		model.addAttribute("cursos", escuelaService.cursos());
		return "matriculas";
		}
	@GetMapping("verMatriculados")
	public String verMatriculados(Model model, @RequestParam int codCurso) {
		model.addAttribute("matriculas", escuelaService.obtenerMatriculas(codCurso));
		model.addAttribute("cursos", escuelaService.cursos());
		model.addAttribute("cursoSel", codCurso);
		return "matriculas";
		}
}
