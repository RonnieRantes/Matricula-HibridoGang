package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.spring.model.Carrera;

public interface ICarreraRepository extends JpaRepository<Carrera,Integer> {

}
