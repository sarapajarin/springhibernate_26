package init.service;

import java.util.List;

import init.dtos.CursoDto;
import init.dtos.MatriculaInfoDto;

public interface EscuelaService {
	List<CursoDto>cursos();
	List<MatriculaInfoDto>obtenerMatriculas(int codCurso);
	boolean matricular(int codCurso, String dni);
}
