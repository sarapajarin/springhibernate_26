package init.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import init.model.Cliente;
import init.model.Libro;
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
	 
	List<Libro>carrito=new ArrayList<Libro>();
	
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
	
	
	
	@GetMapping("añadir")
	public String añadir(HttpSession sesion, Model model, @RequestParam("isbn") int isbn, @RequestParam("temaSel") int idTemaSel) {
		Libro libro=libroService.buscarLibroPorIsbn(isbn);
		List<Libro> carrito=(List<Libro>)sesion.getAttribute("carrito");
		if(carrito!=null) {
			carrito.add(libro);
		}else {
			carrito= new ArrayList<Libro>();
			carrito.add(libro);
			sesion.setAttribute("carrito", carrito);
		}
		model.addAttribute("idTemaSel", idTemaSel);
		model.addAttribute("temas", libroService.temas()); //para que no se pierdan al volver a la página
		model.addAttribute("libros", libroService.librosTema(idTemaSel));
		return "libros";	
	}
	
	@GetMapping("eliminar")
	public String eliminar(Model model, @RequestParam int posicion, HttpSession sesion, @RequestParam("temaSel") int idTemaSel) {
		
		List<Libro> carrito=(List<Libro>)sesion.getAttribute("carrito");
		if(carrito!=null) {
			carrito.remove(posicion);
		}
		model.addAttribute("idTemaSel", idTemaSel);
		model.addAttribute("temas", libroService.temas()); //para que no se pierdan al volver a la página
		model.addAttribute("libros", libroService.librosTema(idTemaSel));
		return "libros";
	}
	
}