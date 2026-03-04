package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data //genera mas cosas ademas de setter y getter
public class Item {
	private String url;
	private String tematica;
	private String descripcion;
	
}
