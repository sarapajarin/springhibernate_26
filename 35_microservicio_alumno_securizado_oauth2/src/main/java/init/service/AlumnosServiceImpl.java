package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.AlumnoDto;
import init.mapper.Mapeador;
import init.model.Alumno;
import init.repository.AlumnosRepository;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class AlumnosServiceImpl implements AlumnosService {
	@Autowired
	AlumnosRepository alumnosRepository;
	@Autowired
	Mapeador mapper;
	

	public List<AlumnoDto> getAllAlumnos(){
		return alumnosRepository.findAll().stream().map(mapper::entityAlumnoToDto).toList();
	}

	public List<AlumnoDto> alumnosByCurso(String curso) {
		return alumnosRepository.findByCurso(curso).stream().map(mapper::entityAlumnoToDto).toList();
	}

	@Override
	public boolean guardar(AlumnoDto alumno) {
		if(!alumnosRepository.findByNombreAndCurso(alumno.getNombre(),alumno.getCurso()).isEmpty()) {
			return false;
		}
		alumnosRepository.save(mapper.dtoAlumnoToEntity(alumno));
		return true;
	}

	public AlumnoDto deleteAlumnoByEmail(String email) {
		if(alumnosRepository.findFirstByEmail(email)!=null) {
			return mapper.entityAlumnoToDto(alumnosRepository.deleteFirstAlumnoByEmail(email));
		}
		return null;
	}

	public List<String> getAllCursos() {
		return alumnosRepository.findCurso();
	}

	public AlumnoDto findByEmail(String email) {
		return mapper.entityAlumnoToDto(alumnosRepository.findFirstByEmail(email));
	}
	
	



}