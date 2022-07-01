package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Seccion;

@Repository
public interface ISeccionRepository extends JpaRepository<Seccion,String> {
	@Query("from Seccion s where s.curso.codigo = :codigo")
	List<Seccion> SeccionesCurso(@Param("codigo") String codigoCurso); //findAll
}
