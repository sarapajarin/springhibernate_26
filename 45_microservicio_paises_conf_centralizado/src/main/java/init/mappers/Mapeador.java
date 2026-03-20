package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.PaisDto;
import init.model.Country;

@Component
public class Mapeador {
	public PaisDto CountryToPais(Country country) {
		return new PaisDto(country.getName(),country.getRegion(),country.getCapital(),country.getPopulation(),country.getFlag());
	}
}
