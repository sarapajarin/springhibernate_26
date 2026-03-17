package init.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import init.model.Country;

@FeignClient(name="countriesFeign", url="${remote.url}")
public interface CountriesFeign {
	@GetMapping("")
	List<Country>getCountries();
}
