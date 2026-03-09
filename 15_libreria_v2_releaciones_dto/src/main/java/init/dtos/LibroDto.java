package init.dtos;

import init.model.Libro;
import init.model.Tema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LibroDto {
	private int isbn;
	private String titulo;
	private String autor;
	private int paginas;
	private double precio;
	//no inclimos un TemaDto porque no necesitamos idTema en esta aplicación
	private String nombreTema;
}
