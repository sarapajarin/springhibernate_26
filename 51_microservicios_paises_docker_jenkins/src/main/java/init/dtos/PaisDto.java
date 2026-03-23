package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaisDto {
	private String nombre;
	private String continente;
	private String capital;
	private long habitantes;
	private String bandera;
}
