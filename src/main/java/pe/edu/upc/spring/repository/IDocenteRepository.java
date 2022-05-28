package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.spring.model.Docente;

public interface IDocenteRepository extends JpaRepository<Docente,String> {

}
