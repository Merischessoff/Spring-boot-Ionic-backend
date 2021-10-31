package com.meridiane.coursemc.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meridiane.coursemc.domain.Cidade;
import com.meridiane.coursemc.domain.Cliente;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer>{
	
}
