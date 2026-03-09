package init.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//LOS DTOS SE CREAN PQ LAS ENTITADES NO SE DEBEN USAR EN VISTAS, SERVICIOS Y CONTROLADORES. 
//NO DEBEN ESTAR EN CONTACTO. SOLO CON DTOS
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoDto {
	private String nombreCurso;
	private int codCurso;
	private int duracion;
	private String horario;
	
}
