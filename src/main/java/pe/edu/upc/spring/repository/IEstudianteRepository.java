package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante,String> {
	@Query("from Estudiante e where e.ingreso.anio = :anio and e.ingreso.periodo = :periodo")
	List<Estudiante> EstudiantesEnSemestre(@Param("anio") int anio, @Param("periodo") int periodo); //findAll
}
