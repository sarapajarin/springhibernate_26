package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import init.service.EstudianteService;

@RestController
public class EstudianteController {
	
	@Autowired
	EstudianteService estudiantesService;
}
