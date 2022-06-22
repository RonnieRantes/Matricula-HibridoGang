package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Docente;

public interface IDocenteRepository extends JpaRepository<Docente,String> {
    @Query("from Docente e where e.ingreso.anio = :anio and e.ingreso.periodo = :periodo")
	List<Docente> DocentesContratadosEnSemestre(@Param("anio") int anio, @Param("periodo") int periodo); //findAll
}
