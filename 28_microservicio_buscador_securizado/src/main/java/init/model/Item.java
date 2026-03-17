package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="items")
public class Item {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idItem;
	private String url;
	private String tematica;
	private String descripcion;
	public Item(String url, String tematica, String descripcion) {
		
		this.url = url;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}
	
	
}
