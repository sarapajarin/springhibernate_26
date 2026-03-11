package init.controller;



import java.util.List;
import init.service.ItemsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.ItemDto;
import init.model.Item;
import init.service.ItemsService;

@RestController
public class ItemsController {

    private final ItemsServiceImpl itemsServiceImpl;

	@Autowired
	ItemsService itemsService;

    ItemsController(ItemsServiceImpl itemsServiceImpl) {
        this.itemsServiceImpl = itemsServiceImpl;
    }
	
	@GetMapping(value="items/{tematica}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemDto>> buscarPorTematica(@PathVariable String tematica){
		return new ResponseEntity<>(itemsService.buscarPorTematica(tematica), HttpStatus.OK);
	}
	
	@PostMapping(value="items", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alta (@RequestBody ItemDto item) {
		if(itemsService.nuevoItem(item)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@DeleteMapping(value= "items", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemDto> delete (@RequestParam String url) {
		return ResponseEntity.ok(itemsService.eliminarItem(url));
	}
}
//RESPONSE ENTITY SIRVE PARA QUE AL CLIENTE NO LE LLEGUE UN 200 OK (EN POSTMAN SI NO USAMOS ESTO SOLO SALE 200 OK
