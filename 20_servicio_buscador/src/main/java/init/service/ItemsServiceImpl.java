package init.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.ItemDto;
import init.mapper.Mapeador;
import init.model.Item;
import init.repository.ItemsRepository;
@Service
public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	ItemsRepository itemsRepository;
	@Autowired
	Mapeador mapeador;
	
	@Override
	public List<ItemDto> buscarPorTematica(String tematica) {
		return itemsRepository.findByTematica(tematica)
				.stream()
				.map(c -> mapeador.itemEntityToDto(c))
				.toList();
				
		
	}
	@Override
	public boolean nuevoItem(ItemDto item) {
		if(itemsRepository.findFirstByUrl(item.getUrl())==null) {
			itemsRepository.save(mapeador.itemDtoToEntity(item));
			return true;
		}
		return false;
		
	}
	
	public ItemDto eliminarItem(String url) {
		Item item=itemsRepository.findFirstByUrl(url);
		if(item != null) {
			itemsRepository.deleteByUrl(url);
		}
		return mapeador.itemEntityToDto(item);
	}
}
