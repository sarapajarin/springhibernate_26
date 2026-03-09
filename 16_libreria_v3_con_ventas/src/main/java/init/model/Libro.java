package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="libros")
public class Libro {
	@Id
	private int isbn;
	private String titulo;
	private String autor;
	private int paginas;
	private double precio;
	@ManyToOne()
	@JoinColumn(name="idTema",referencedColumnName = "idTema")
	private Tema temaRelacionado;
}
