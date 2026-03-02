package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AlumnosRepository;
import model.Alumno;

@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@Autowired
	AlumnosRepository alumnosRepository;
	@Override
	public boolean guardar(Alumno alumno) {
		if(alumnosRepository.findByNombreAndCurso(alumno.getNombre(), alumno.getCurso())== null) {
			alumnosRepository.save(alumno);
			return true;
			}
			return false;
		}
	@Override
	public List<String> mostrarCursos() {
		return alumnosRepository.findAllCursos();
	}

	@Override
	public List<Alumno> mostrarAlumnosCurso(String curso) {
		return alumnosRepository.findByCurso(curso);
	}
	
}


