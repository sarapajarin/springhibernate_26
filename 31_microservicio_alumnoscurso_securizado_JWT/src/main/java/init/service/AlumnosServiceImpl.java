package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.AlumnoDto;
import init.mapper.Mapeador;
import init.model.Alumno;
import init.repository.AlumnosRepository;

@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@Autowired
	AlumnosRepository alumnosRepository;
	@Autowired
	Mapeador mapeador;
	
	@Override
	public boolean guardar(AlumnoDto alumno) {
		Alumno alumnoAux =mapeador.alumnoDtoToEntity(alumno);
		if(alumnosRepository.findFirstByNombreAndCurso(alumno.getNombre(), alumno.getCurso())== null) {
			alumnosRepository.save(alumnoAux);
			return true;
			}
			return false;
		}
	@Override
	public List<String> mostrarCursos() {
		return alumnosRepository.findAllCursos();
	}

	@Override
	public List<AlumnoDto> mostrarAlumnosCurso(String curso) {
		return alumnosRepository.findByCurso(curso)
				.stream().map(a -> mapeador.alumnoEntityToDto(a))
				.toList();
	}   
	@Override
	public AlumnoDto eliminar (String email) {
		
		if(alumnosRepository.findFirstByEmail(email)!=null) {
			alumnosRepository.delete(alumnosRepository.findFirstByEmail(email));
			return mapeador.alumnoEntityToDto(alumnosRepository.findFirstByEmail(email));
		}
		
		return null;
		
	}
	
}


