package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.spring.model.Curso;

public interface ICursoRepository extends JpaRepository<Curso,Integer>{

}