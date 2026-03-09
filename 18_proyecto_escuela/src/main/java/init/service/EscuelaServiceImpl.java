package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dtos.CursoDto;
import init.dtos.MatriculaInfoDto;
import init.mapper.Mapeador;
import init.repository.AlumnosRepository;
import init.repository.CursosRepository;

@Service
public class EscuelaServiceImpl implements EscuelaService {
	
	@Autowired
	CursosRepository cursosRepository;
	@Autowired
	AlumnosRepository alumnosRepository;
	@Autowired
	Mapeador mapeador;
	
	@Override
	public List<CursoDto> cursos() {
		return cursosRepository.findAll()
				.stream()
				.map(t -> mapeador.cursoEntityToDto(t))
				.toList();
	}

	@Override
	public List<MatriculaInfoDto> obtenerMatriculas(int codCurso) {
		CursoDto curso = cursosRepository.findById(codCurso) //Optional<Curso>
				.map(c -> mapeador.cursoEntityToDto(c)) //Optional<CursoDto>
				.orElse(null);
		//si el curso no existe, devolvemos lista vacia
		if(curso==null) {
			return List.of();
		}
		return alumnosRepository.findByCodigoCurso(codCurso)//List<Alumno>
				.stream()//Stream<Alumno>
				.map(a -> new MatriculaInfoDto(mapeador.alumnoEntityToDto(a), curso))//stream<MatriculaInfoDto>
				.toList();
	}

	@Override
	public boolean matricular(int codCurso, String dni) {
		//matricular es salvar el curso con su lista de alumnos, en la que incluye el nuevo alumno.
		if(cursosRepository.findById(codCurso).isPresent() && alumnosRepository.findById(dni).isPresent()) { //los metodos de jpa crud no devuelven nunca null, por eso usamos is present
			cursosRepository.matricular(codCurso, dni);
			return true;
		}
		return false;
	}

}
