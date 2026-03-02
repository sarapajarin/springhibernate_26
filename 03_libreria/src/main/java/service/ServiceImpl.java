package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;


import model.Libro;

@Service
public class ServiceImpl implements LibrosService {
	static List<Libro> libreria=new ArrayList<>(Arrays.asList(new Libro("978-3-16-148410-0","El Eco de los Algoritmos","Elena Casals", 22.50 ),
			new Libro ("978-1-23-456789-7","Sombras en la Red","Marco Aurelio Soto",18.95),
			new Libro ("978-0-59-652068-7","El Guardián del Silencio","Clara P. Montes",15.00),
			new Libro ("978-8-42-067661-6","Más Allá del Horizonte","Javier Villalobos",27.30)
			));
	public Libro buscar(String ISBN){
		return libreria.stream()
				.filter(l->l.getISBN().equals(ISBN))
				.findFirst()
				.orElse(null);
	}
	public boolean altaLibro (Libro libro) {
		for(Libro it:libreria) {
			if(it.getISBN().equals(libro.getISBN())) {
				return false;
			}
		}
		return libreria.add(libro);
	}
	public boolean bajaLibro (String ISBN) {
		if(buscar(ISBN)==null) {
			return false;
		}
		return libreria.removeIf(l -> l.getISBN().equals(ISBN));
	}
		
	
	
	}

