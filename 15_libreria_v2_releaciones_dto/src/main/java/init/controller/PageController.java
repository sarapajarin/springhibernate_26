package init.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//controlador para navegaciones etáticas
@Controller
public class PageController {

	@GetMapping({"/","goLogin"})
	public String inicio() {
		return "login";
	}
	@GetMapping("goRegistro")
	public String goRegistro() {
		return "registro";
	}
}
