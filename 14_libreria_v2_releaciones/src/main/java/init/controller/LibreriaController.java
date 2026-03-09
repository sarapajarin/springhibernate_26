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
import init.service.ClientesService;
import init.service.LibrosService;
import jakarta.servlet.http.HttpSession;
//@RequestMapping("clientes")
@Controller
public class LibreriaController {
	@Autowired
	ClientesService clientesService;
	
	@Autowired
	LibrosService librosService;
	
	@GetMapping("login")
	public String autenticar(HttpSession sesion, Model model, @RequestParam("usuario") String usuario, 
			@RequestParam("password") String password) {
		Cliente cliente=clientesService.autenticarUsuario(usuario, password);
		if(cliente!=null) {
			//guardamos el nombre del usuario en la sesión
			sesion.setAttribute("usuario", cliente.getUsuario());
			model.addAttribute("temas", librosService.temas());
			return "libros";
		}else {
			model.addAttribute("mensaje", "Cliente no válido!!");
			return "mensaje";
		}
	}
	@PostMapping("alta")
	public String registrar(Model model, @ModelAttribute Cliente cliente) {
		
		if(!clientesService.guardar(cliente)) {
			model.addAttribute("mensaje", "No se puede repetir nombre de usuario!!");
			return "mensaje";
		}
		return "login";
	}
	@GetMapping("libros")
	public String verLibros(Model model, @RequestParam("temaSel") int idTemaSel) {
		model.addAttribute("idTemaSel", idTemaSel);
		model.addAttribute("temas", librosService.temas()); //para que no se pierdan al volver a la página
		model.addAttribute("libros", librosService.librosTema(idTemaSel));
		return "libros";
	}
	
	@GetMapping("agregar")
	public String agregar(Model model, 
			@RequestParam("temaSel") int idTemaSel,
			@RequestParam("isbn") int isbn,HttpSession sesion) {
		//1. buscar el libro
		//2. Recupera el carrito de sesión (es una list)
		//3. Si es distinto a null, guardar en la lista el libro
		//3b. Si es null, crea la lista en ese momento, añade el libro, y guarda la lista en sesión
		//4. volvemos a la página
		Libro libro=librosService.buscarLibroPorIsbn(isbn);
		List<Libro> carrito=(List<Libro>)sesion.getAttribute("carrito");
		if(carrito!=null) {
			carrito.add(libro);
		}else {
			carrito=new ArrayList<>();
			carrito.add(libro);
			sesion.setAttribute("carrito", carrito);
		}
		//preparar la vista
		model.addAttribute("idTemaSel", idTemaSel);
		model.addAttribute("temas", librosService.temas()); //para que no se pierdan al volver a la página
		model.addAttribute("libros", librosService.librosTema(idTemaSel));
		return "libros";
		
	}
	@GetMapping("quitar")
	public String eliminar(Model model,
			@RequestParam("temaSel") int idTemaSel,
			@RequestParam("posicion") int posicion,HttpSession sesion) {
		//1. Recuperamos el carrito
		//2. Si es distinto de null, eliminamos la fila que tenga esa posición
		//3. Volvemos a la página
		List<Libro> carrito=(List<Libro>)sesion.getAttribute("carrito");
		if(carrito!=null) {
			carrito.remove(posicion);
		}
		//preparar la vista
		model.addAttribute("idTemaSel", idTemaSel);
		model.addAttribute("temas", librosService.temas()); //para que no se pierdan al volver a la página
		model.addAttribute("libros", librosService.librosTema(idTemaSel));
		return "libros";
	}
	
	
}
