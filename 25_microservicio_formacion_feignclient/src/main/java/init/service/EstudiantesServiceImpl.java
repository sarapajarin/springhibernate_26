package init.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import feign.FeignException;
import init.clients.AlumnosFeign;
import init.dtos.EstudianteDto;
import init.mappers.Mapeador;
import init.model.Alumno;

@Service
public class EstudiantesServiceImpl implements EstudianteService {
	
	@Autowired
	RestClient restClient;
	@Autowired
	Mapeador mapeador;
	@Autowired
	AlumnosFeign alumnosFeign;
	
	@Value("${remote.url}")
	private String urlBase;
	
	@Override
	public List<EstudianteDto> estudiantesRangoCalificaciones(double min, double max) {
		
		return alumnosFeign.alumnos().stream()
				.map(a->mapeador.alumnoToEstudiante(a))//Stream<EstudianteDto>
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}


	@Override
	public boolean alta(EstudianteDto estudiante) {
		
		try {
			alumnosFeign.nuevoAlumno(mapeador.estudianteToAlumno(estudiante));
			return true;
		}catch(FeignException ex) {
			if(ex.status()==409) {
				return false;
			}
			throw ex;
		}
		}
	}


