package init.service;

import java.util.List;

import init.dtos.PaisDto;

public interface PaisesService {
	List<String>continentes();
	List<PaisDto>paisesPorContiente(String continente);
}
