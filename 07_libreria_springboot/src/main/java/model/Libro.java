package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor //crea constructor
@NoArgsConstructor //constructor sin argumentos
@Setter
@Getter
public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private double precio;
}
