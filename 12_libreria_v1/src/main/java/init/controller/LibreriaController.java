package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.model.Cliente;
import init.service.ClienteService;
import init.service.LibroService;
import init.service.TemaService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LibreriaController {
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	LibroService libroService;
	
	@GetMapping("login")
	public String autenticar(HttpSession sesion, Model model, @RequestParam("usuario") String usuario, 
			@RequestParam("password") String password) {
		Cliente cliente=clienteService.autenticarUsuario(usuario, password);
		if(cliente!=null) {
			//guardamos el nombre del usuario en la sesión
			sesion.setAttribute("usuario", cliente.getUsuario());
			model.addAttribute("temas", libroService.temas());
			return "libros";
		}else {
			model.addAttribute("mensaje", "Cliente no válido!!");
			return "mensaje";
		}
	}
	@PostMapping("alta")
	public String registrar(Model model, @ModelAttribute Cliente cliente) {
		
		if(!clienteService.guardar(cliente)) {
			model.addAttribute("mensaje", "No se puede repetir nombre de usuario!!");
			return "mensaje";
		}
		return "login";
	}
	@GetMapping("libros")
	public String verLibros(Model model, @RequestParam("temaSel") int idTemaSel) {
		model.addAttribute("idTemaSel", idTemaSel);
		model.addAttribute("temas", libroService.temas()); //para que no se pierdan al volver a la página
		model.addAttribute("libros", libroService.librosTema(idTemaSel));
		return "libros";
	}
	
	
	
	
}