package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Horario;

@Repository
public interface IHorarioRepository  extends JpaRepository<Horario, Integer>{
	@Query("from Horario h where h.seccion.codigo = :codigo")
	List<Horario> HorariosSeccion(@Param("codigo") String codigoSeccion); //findAll

	@Query("from Horario h where h.dia = :dia")
	List<Horario> HorariosDia(@Param("dia") int dia); //findAll
}
