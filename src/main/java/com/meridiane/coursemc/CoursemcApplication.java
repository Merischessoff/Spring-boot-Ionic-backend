package com.meridiane.coursemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.meridiane.coursemc.domain.Categoria;
import com.meridiane.coursemc.domain.Produto;
import com.meridiane.coursemc.repositories.CategoriaRepository;
import com.meridiane.coursemc.repositories.ProdutoRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner/*Ao implementar esta interface classe é obrigada a implementar método run*/ {
	
	@Autowired //Instancia automaticamente CategoriaRepository
	private CategoriaRepository categorias;
	
	@Autowired //Instancia automaticamente ProdutoRepository
	private ProdutoRepository produtos;

	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica"); 
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));


		categorias.saveAll(Arrays.asList(cat1,cat2));
		produtos.saveAll(Arrays.asList(p1, p2, p3));
	}

}
 