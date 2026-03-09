package init.mapper;

 
import org.springframework.stereotype.Component;

import init.dtos.AlumnoDto;
import init.dtos.CursoDto;
import init.dtos.MatriculaInfoDto;
import init.model.Alumno;
import init.model.Curso;


@Component
public class Mapeador {
	public AlumnoDto alumnoEntityToDto(Alumno alumno) {//metodos y orden deenity
		return new AlumnoDto(alumno.getNombre(), alumno.getDni(), alumno.getEmail(),alumno.getEdad());
	}
	public Alumno alumnoDtoToEntity(AlumnoDto alumno) {//metodos y orden de dto
		return new Alumno(alumno.getDni(), alumno.getNombre(), alumno.getEmail(), alumno.getEdad());
	}
	public CursoDto cursoEntityToDto(Curso curso) {//usamos metodos de entity
		return new CursoDto(curso.getDenominacion(), curso.getCodCurso(), curso.getDuracion(), curso.getHorario());
	}
	public Curso cursoDtoToEntity(CursoDto curso) {//usamos metodos de dto
		return new Curso(curso.getCodCurso(),curso.getNombreCurso(), curso.getDuracion(), curso.getHorario());
	}
	public MatriculaInfoDto matriculaDto(CursoDto curso, AlumnoDto alumno) {
		return new MatriculaInfoDto(alumno, curso);
	}
}
