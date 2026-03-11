package init.service;

import java.util.List;

import init.dtos.ItemDto;
import init.model.Item;

public interface ItemsService {
	List<ItemDto> buscarPorTematica(String tematica);
	public ItemDto eliminarItem(String url);
	boolean nuevoItem(ItemDto item);
}
