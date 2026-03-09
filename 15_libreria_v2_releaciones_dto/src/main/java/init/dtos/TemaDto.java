package init.dtos;

import java.util.List;

import init.model.Libro;
import init.model.Tema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemaDto {
	private int idTema;
	private String tema;
}
