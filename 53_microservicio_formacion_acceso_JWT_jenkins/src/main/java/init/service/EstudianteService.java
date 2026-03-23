package init.service;

import java.util.List;

import init.dtos.EstudianteDto;


public interface EstudianteService {
	List<EstudianteDto> estudiantesRangoCalificaciones(double min, double max);
	boolean alta(EstudianteDto estudiante);
}
