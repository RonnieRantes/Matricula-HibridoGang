package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.spring.model.Rol;

public interface IRolRepository extends JpaRepository<Rol, Integer>{
	//Implementado en JpaRepository
}