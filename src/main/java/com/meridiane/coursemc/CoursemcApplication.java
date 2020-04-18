package com.meridiane.coursemc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.meridiane.coursemc.domain.Categoria;
import com.meridiane.coursemc.repositories.CategoriaRepository;
import java.util.Arrays;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner/*Ao implementar esta interface classe é obrigada a implementar método run*/ {
	
	@Autowired //Instancia automaticamente CategoriaRepository
	private CategoriaRepository categorias;

	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "meridiane"); 
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categorias.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
 