package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.CursoCarrera;

@Repository
public interface ICursoCarreraRepository extends JpaRepository<CursoCarrera,Integer>  {
	@Query("from CursoCarrera cc where cc.carrera.idCarrera = :carrera")
	List<CursoCarrera> CursosEnCarrera(@Param("carrera") int idCarrera); //findAll
}

