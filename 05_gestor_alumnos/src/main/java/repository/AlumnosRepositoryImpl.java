package repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Alumno;


@Repository
public class AlumnosRepositoryImpl implements AlumnosRepository {
	
	@PersistenceContext
	EntityManager eManager;
	
	@Transactional//todo lo que modifica la base de datos tiene que tener esta anotacion
	@Override
	public void save(Alumno alumno) {
		eManager.persist(alumno);
	}

	@Override
	public List<Alumno> findByCurso(String curso) {
		String jpql="select i from Alumno i where i.curso=?1";
		TypedQuery<Alumno> query=eManager.createQuery(jpql,Alumno.class);
		query.setParameter(1, curso);
		return query.getResultList();
		
	}

	@Override
	public Alumno findByNombreAndCurso(String nombre, String curso) {
		String jpql="select i from Alumno i where i.nombre=?1 and i.curso=?2";
		TypedQuery<Alumno> query=eManager.createQuery(jpql,Alumno.class);
		query.setParameter(1, curso);
		query.setParameter(2, nombre);
		List<Alumno> resultado=query.getResultList();
		return resultado.size()>0?resultado.get(0):null;
	}

	@Override
	public List<String> findAllCursos() {
		String jpql = "select distinct(a.curso) from Alumno a";
		TypedQuery<String> query=eManager.createQuery(jpql,String.class);
		return query.getResultList();
		
	}
	
	@Transactional
	@Override
	public void removeById(int idAlumno) {
//		String jpql="delete from Alumno a where a.idAlumno=?1";
//		Query query= eManager.createQuery(jpql);
//		query.setParameter(1, idAlumno);
//		query.executeUpdate();
		Alumno a=eManager.find(Alumno.class, idAlumno);
		if(a!=null) {
			eManager.remove(a);
		}
	}

	@Override
	public double averageByCurso(String curso) { ///////////////////USANDO SQL EN VEZ DE JPQL
		String sql= "select avg(nota) from alumnos where curso=?";
		Query query= eManager.createNativeQuery(sql, Double.class);
		query.setParameter(1, curso);
		return (double) query.getSingleResult();
	}

}
