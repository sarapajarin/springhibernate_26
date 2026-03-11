package init.mapper;

import org.springframework.stereotype.Component;

import init.dtos.ItemDto;
import init.model.Item;

@Component
public class Mapeador {
	
	public ItemDto itemEntityToDto(Item item) {
		return new ItemDto(item.getIdItem(),item.getUrl(), item.getTematica(),item.getDescripcion());
	}
	public Item itemDtoToEntity(ItemDto item) {
		return new Item(item.getIdItem(),item.getUrl(),item.getTematica(),item.getDescripcion());
	}
}
