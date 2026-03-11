package init.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;

import init.dtos.EstudianteDto;
import init.mappers.Mapeador;
import init.model.Alumno;

public class EstudiantesServiceImpl implements EstudianteService {
	
	@Autowired
	RestClient restClient;
	@Autowired
	Mapeador mapeador;
	
	private String urlBase="http://localhost:8005/escuela/";
	
	@Override
	public List<EstudianteDto> estudiantesRangoNotas(double min, double max) {
		
		return Arrays.stream(restClient.get()
				.uri(urlBase+"alumnos")
				.retrieve()
				.body(Alumno[].class)) //convertimos json en stream<Alumno>
				.map(a -> mapeador.alumnoToEstudiante(a))//Stream<Estudiante>
				.toList();//Stream<Estudiante>
	}

	@Override
	public boolean alta(EstudianteDto estudiante) {
		// TODO Auto-generated method stub
		return false;
	}

}
