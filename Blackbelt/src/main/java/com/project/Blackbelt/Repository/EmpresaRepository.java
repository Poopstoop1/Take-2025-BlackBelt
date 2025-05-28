package com.project.Blackbelt.Repository;



import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.project.Blackbelt.Model.Empresa;

import jakarta.transaction.Transactional;

/**
 * Repositório responsável por operações de acesso aos dados da entidade {@link Empresa}.
 *
 * Fornece métodos personalizados de consulta, além dos já implementados pelo {@link JpaRepository}.
 * 
 * @author Poopstoop1
 * @version 1.0
 * @since Java 21 (JDK 21)
 */
@Repository
@Transactional
public interface EmpresaRepository extends CrudRepository<Empresa, String>{
	Empresa findByNome(String nome);
	
	
}
