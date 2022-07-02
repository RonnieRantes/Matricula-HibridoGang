package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Matricula;

@Repository
public interface IMatriculaRepository extends JpaRepository<Matricula, Integer>{
	@Query("from Matricula m where m.estudiante.codigo = :codigo and m.semestre.idSemestre = :idSemestre")
	List<Matricula> MatriculaEstudianteSemestre(@Param("codigo") String codigoEstudiante, @Param("idSemestre") int idSemestre); //findAll

	@Query("from Matricula m where m.estudiante.codigo = :codigo and m.semestre.idSemestre = :idSemestre and m.seccion.curso.codigo = :codigoCurso")
	List<Matricula> MatriculaEstudianteSemestreCurso(@Param("codigo") String codigoEstudiante, @Param("idSemestre") int idSemestre, @Param("codigoCurso") String codigoCurso); //findAll
	
	@Query("from Matricula m where m.semestre.idSemestre = :idSemestre and m.seccion.codigo = :codigoSeccion")
	List<Matricula> MatriculaSeccion(@Param("idSemestre") int idSemestre, @Param("codigoSeccion") String codigoSeccion); //findAll
		
	@Query("from Matricula m where m.semestre.idSemestre = :idSemestre")
	List<Matricula> MatriculaSemestre(@Param("idSemestre") int idSemestre); //findAll
	
		@Query("from Matricula m where m.estudiante.codigo = :codigoE and m.seccion.codigo = :codigoS")
	List<Matricula> MatriculaEstudianteSeccion(@Param("codigoE") String codigoEstudiante, @Param("codigoS") String codigoSeccion); //findAll

}
