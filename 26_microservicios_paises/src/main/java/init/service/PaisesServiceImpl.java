package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.clients.CountriesFeign;
import init.dtos.PaisDto;
import init.mappers.Mapeador;

@Service
public class PaisesServiceImpl implements PaisesService {

	@Autowired
	CountriesFeign countriesFeign;
	@Autowired
	Mapeador mapeador;
	
	@Override
	public List<String> continentes() {
		return countriesFeign.getCountries().stream().map(c -> c.getRegion())
				.distinct() //eliminar duplicados
				.toList();
	}

	@Override
	public List<PaisDto> paisesPorContiente(String contiente) {
		return countriesFeign.getCountries()
				.stream().map(c -> mapeador.CountryToPais(c) )
				.filter(p -> p.getContinente().equals(contiente))
				.toList();
	}

}
