package init.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import feign.FeignException;
import init.clients.AlumnosFeign;
import init.clients.Oauth2Feign;
import init.dtos.CredentialsDto;
import init.dtos.EstudianteDto;
import init.dtos.TokenDto;
import init.mappers.Mapeador;

@Service
public class EstudiantesServiceImpl implements EstudianteService, InitializingBean {
	
	@Autowired
	RestClient restClient;
	@Autowired
	Mapeador mapeador;
	@Autowired
	AlumnosFeign alumnosFeign;
	@Autowired
	Oauth2Feign oauth2Feign;
	
	@Value("${remote.url}")
	private String urlBase;
	@Value("${remote.password}")
	private String pass;
	@Value("${remote.user}")
	private String user;
	@Value("${oauth2.clientId}")
	private String clientId;
	@Value("${oauth2.grantType}")
	private String grantType;
	
	private TokenDto tokenRecibido;
	
	@Override
	public List<EstudianteDto> estudiantesRangoCalificaciones(double min, double max) {
		
		return alumnosFeign.alumnos("Bearer "+tokenRecibido.getAccess_token()).stream()
				.map(a->mapeador.alumnoToEstudiante(a))//Stream<EstudianteDto>
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}


	@Override
	public boolean alta(EstudianteDto estudiante) {
		
		try {
			alumnosFeign.nuevoAlumno(mapeador.estudianteToAlumno(estudiante),"Bearer "+tokenRecibido.getAccess_token());
			return true;
		}catch(FeignException ex) {
			if(ex.status()==409) {
				return false;
			}
			throw ex;
		}
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		cargarToken();
	}
	
	private void cargarToken() {
		MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", user);
		params.add("password", pass);
		params.add("grant_type", grantType);
		tokenRecibido = oauth2Feign.autenticar(params);
	}
}


