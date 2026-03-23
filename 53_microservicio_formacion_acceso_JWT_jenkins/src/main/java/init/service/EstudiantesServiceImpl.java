package init.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.dtos.CredentialsDto;
import init.dtos.EstudianteDto;
import init.mappers.Mapeador;
import init.model.Alumno;

@Service
public class EstudiantesServiceImpl implements EstudianteService, InitializingBean {
	
	@Autowired
	RestClient restClient;
	@Autowired
	Mapeador mapeador;
	
	@Value("${remote.url}")
	private String urlBase;
	@Value("${remote.password}")
	private String pass;
	@Value("${remote.user}")
	private String user;
	
	private String tokenRecibido;
	
	@Override
	public List<EstudianteDto> estudiantesRangoCalificaciones(double min, double max) {
		return Arrays.stream(restClient.get()
				.uri(urlBase+"alumnos")
				.header("Authorization", "Bearer "+tokenRecibido)
				.retrieve()
				.body(Alumno[].class))  //Stream<Alumno>
				.map(a->mapeador.alumnoToEstudiante(a))//Stream<EstudianteDto>
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}


	@Override
	public boolean alta(EstudianteDto estudiante) {
		try {
		restClient.post()
		.uri(urlBase+"alumnos")
		.header("Authorization", "Bearer"+tokenRecibido)
		.contentType(MediaType.APPLICATION_JSON)
		.body(mapeador.estudianteToAlumno(estudiante))
		.retrieve()
		.toBodilessEntity();
		return true;
		}catch(HttpClientErrorException ex){
			if(ex.getStatusCode()==HttpStatus.CONFLICT) {
				return false;
			}
			throw ex;
			}
		}
	

	//se ejecuta tras creacion del objeto estuduieantes servic una vez que lkas propiedades han sido establecidas
	@Override
	public void afterPropertiesSet() throws Exception {
		cargarToken();
	}
	private void cargarToken() {
		CredentialsDto credential= new CredentialsDto(user, pass);
		try {
			restClient.post()
			.uri(urlBase+"login")
			.contentType(MediaType.APPLICATION_JSON)
			.body(credential)
			.retrieve()
			.body(String.class);
		}catch(HttpClientErrorException ex) {
			throw ex;
		}
		
	}
}


