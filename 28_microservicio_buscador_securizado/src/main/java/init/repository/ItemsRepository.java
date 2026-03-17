package init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.dtos.ItemDto;
import init.model.Item;

public interface ItemsRepository extends JpaRepository<Item, Integer> {
	List<Item> findByTematica(String tematica);
	
	Item findFirstByUrl(String url);
	//ELIMINA ITEM POR URL:
	@Transactional
    @Modifying
	void deleteByUrl(String url);
	//metodo que devuelva todos los items cuya descripcion contenga el texto recibido como parametro
	@Query("select i from Item i where i.descripcion like %?1%")
	List<Item> findLikeDescripcion(String descripcion);
	//List<Item> findByDescriptionLike(String texto);
	//metodo que devuelve el total de items de una det tematica
	@Query(value="select count(*) from items where tematica =?",nativeQuery=true)
	int countByTematica(String tematica);
	void save(ItemDto item);
}
