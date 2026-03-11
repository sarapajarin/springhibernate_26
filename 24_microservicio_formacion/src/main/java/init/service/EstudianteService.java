package init.service;

import java.util.List;

import init.dtos.EstudianteDto;


public interface EstudianteService {
	List<EstudianteDto> estudiantesRangoNotas(double min, double max);
	boolean alta(EstudianteDto estudiante);
}
