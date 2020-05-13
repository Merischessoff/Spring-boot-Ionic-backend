package com.meridiane.coursemc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.meridiane.coursemc.domain.Categoria;
import com.meridiane.coursemc.domain.Cidade;
import com.meridiane.coursemc.domain.Cliente;
import com.meridiane.coursemc.domain.Endereco;
import com.meridiane.coursemc.domain.Estado;
import com.meridiane.coursemc.domain.Pagamento;
import com.meridiane.coursemc.domain.PagamentoComBoleto;
import com.meridiane.coursemc.domain.PagamentoComCartao;
import com.meridiane.coursemc.domain.Pedido;
import com.meridiane.coursemc.domain.Produto;
import com.meridiane.coursemc.domain.enums.EstadoPagamento;
import com.meridiane.coursemc.domain.enums.TipoCliente;
import com.meridiane.coursemc.repositories.CategoriaRepository;
import com.meridiane.coursemc.repositories.CidadeRepository;
import com.meridiane.coursemc.repositories.ClienteRepository;
import com.meridiane.coursemc.repositories.EnderecoRepository;
import com.meridiane.coursemc.repositories.EstadoRepository;
import com.meridiane.coursemc.repositories.PagamentoRepository;
import com.meridiane.coursemc.repositories.PedidoRepository;
import com.meridiane.coursemc.repositories.ProdutoRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner/*Ao implementar esta interface classe é obrigada a implementar método run*/ {
	//Criando dependências
	@Autowired //Instancia automaticamente CategoriaRepository
	private CategoriaRepository categoriaRepository;
	
	@Autowired //Instancia automaticamente ProdutoRepository
	private ProdutoRepository produtoRepository;
	
	@Autowired //Instancia automaticamente
	private CidadeRepository cidadeRepository;
	
	@Autowired //Instancia automaticamente
	private EstadoRepository estadoRepository;
	
	@Autowired //Instancia automaticamente
	private ClienteRepository clienteRepository;
	
	@Autowired //Instancia automaticamente
	private EnderecoRepository enderecoRepository;
	
	@Autowired //Instancia automaticamente
	private PedidoRepository pedidoRepository;
	
	@Autowired //Instancia automaticamente
	private PagamentoRepository pagamentoRepository;
	

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

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "000000000000000", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1,c1);
		Endereco e2 = new Endereco(null, "Avenida Maia", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	}

}
 