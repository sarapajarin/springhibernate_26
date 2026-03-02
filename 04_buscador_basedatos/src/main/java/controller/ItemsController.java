package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Item;
import service.ItemsService;
import service.ItemsServiceImpl;

@Controller
public class ItemsController {
	@Autowired
	ItemsService itemsService;    //ponemos ahi requestparam para que se pasen por parametros en la url
	@GetMapping("buscar")
	public String buscarItems(Model model,@RequestParam("tematica") String tematica) {
		List<Item> items=itemsService.buscarPorTematica(tematica);
		//guardar la lista en algún lugar donde pueda recogerlo
		//la página que va a generar la respuesta
		model.addAttribute("listado", items);
		System.out.println(items);
		return "resultados";
		
	}

	@PostMapping("alta")
	public String añadir(Model model,
			@RequestParam("url")String url,
			@RequestParam("tematica")String tematica,
			@RequestParam("descripcion")String descripcion) {
		Item item= new Item(url, tematica,descripcion);
		
		if(itemsService.nuevoItem(item)) {
			model.addAttribute("mensaje", "Item añadido correctamente!");
		}else {
			model.addAttribute("mensaje", "Item NO, añadido URL duplicada");
		}
		return "mensaje";
	}
	@GetMapping({"inicio"})//podemos asociar varias direcciones en un metodo
	public String inicio() {
		return "inicio";
	}
	@GetMapping({"nuevo"})//podemos asociar varias direcciones en un metodo
	public String nuevo() {
		return "nuevo";
	}
	@GetMapping({"/","menu"})//podemos asociar varias direcciones en un metodo
	public String menu() {
		return "menu";
	}
	
}
