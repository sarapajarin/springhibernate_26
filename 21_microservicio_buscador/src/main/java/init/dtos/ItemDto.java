package init.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemDto {
	private int idItem;
	private String url;
	private String tematica;
	private String descripcion;
}
