package init.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//agrupa navegaciones estaticas
@Controller
public class PageController {

	@GetMapping({"/", "goLogin"})
	public String goLogin() {
		return "login";
	}
	@GetMapping("goRegistro")
	public String goAlta() {
		return "registro";
	}
}
