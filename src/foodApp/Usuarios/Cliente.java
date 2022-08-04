package foodApp.Usuarios;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import foodApp.Lanchonetes.ComparaLanchonetePontos;
import foodApp.Lanchonetes.ComparaLanchonetePrecoMedio;
import foodApp.Lanchonetes.Lanchonete;

public class Cliente extends Usuario {

	
	public Cliente(Sistema sistema) {
		super(sistema);
		this.ident = 3;
	}

	public Cliente(ArrayList<String> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void menu() {
		Scanner s = new Scanner (System.in);
		int opcao;
		
		do {
		System.out.println("------------------------------------------------");
		System.out.println("              MENU CLIENTE                      ");
		System.out.println("------------------------------------------------");
		System.out.println("1) Listar todas as lanchonetes por pontuação");
		System.out.println("2) Listar lanchonetes de uma categoriapor pontuação");
		System.out.println("3) Listar lanchonetes por preco");
		System.out.println("4) Listar lanchonetes pelo numero de vendas");
		System.out.println("5) Ver lanches de uma lanchonete");
		System.out.println("6) Buscar lanche");
		System.out.println("7) Fazer pedido");
		System.out.println("8) Remover cadastro");
		System.out.println("0) Sair");


		opcao = s.nextInt();
		switch(opcao) {
		case 1:
			listarLanchonetesPontuacao();
			break;
		case 2:
			listarPontuacaoCategoria();
			break;
		case 3:
			listarLanchonetesPrecoMedio();
			break;
		case 4:
			break;
		case 5:
			verLanchesLanchonete();
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			removerCadastro(this.getEmail(), sistema.getListaUsuarios());
			break;
		case 0:
			break;
		default:
			System.out.println("Opcao invalida! Tente novamente.");
			break;
		}	
		}while(opcao!=0);
	}


	public void exibirLanchonetes() {	
		System.out.println("---------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%15s", "CODIGO", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		for(Lanchonete l : sistema.getTodasLanchonetes()) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s%15s",l.codigo, l.nome, l.endereco, l.categoria, l.pontos);
			System.out.println();
			System.out.println("---------------------------------------------------------");
		}
			
	}
	
	public Lanchonete buscarLanchonete(int codigo) {
		for(Lanchonete l : sistema.getTodasLanchonetes())
			if(l.codigo == codigo)
				return l;
		return null;
	}
	
	
	public void listarLanchonetes() {
		System.out.println("---------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%15s", "CODIGO", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		for(Lanchonete l : sistema.getTodasLanchonetes()) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s%15s",l.codigo, l.nome, l.endereco, l.categoria, l.pontos);
			System.out.println();
			System.out.println("---------------------------------------------------------");
			
		}
	}
	
	public void verLanchesLanchonete() {
		listarLanchonetes();
		System.out.println("Qual lanchonete gostaria de visualizar?");
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else {
			l.listarLanches();
		}
	}
	
	//case 1
	public void listarLanchonetesPontuacao() {
		//arq.lerLanchonetesArq(lanchonetes);
		ComparaLanchonetePontos c = new ComparaLanchonetePontos();
		Collections.sort(sistema.getTodasLanchonetes(), c);
		System.out.println(sistema.getTodasLanchonetes());
	}
	
	//case 2
	public void listarPontuacaoCategoria() {
		System.out.print("Categoria das lanchonetes a serem exibidas:");
		String categoria = s.nextLine();
		ArrayList<Lanchonete> lanchonete = new ArrayList<>();
		for(Lanchonete l : sistema.getTodasLanchonetes()) {
			if(l.categoria.equals(categoria))
				lanchonete.add(l);
		}
		ComparaLanchonetePontos c = new ComparaLanchonetePontos();
		Collections.sort(lanchonete, c);
		System.out.println(lanchonete);
	}
	
	//case 3
	public void listarLanchonetesPrecoMedio() {
		//arq.lerLanchonetesArq(lanchonetes);
		ComparaLanchonetePrecoMedio c = new ComparaLanchonetePrecoMedio();
		Collections.sort(sistema.getTodasLanchonetes(), c);
		//listarLanchonetes();
		//System.out.println(lanchonetes);
		exibirLanchonetes();
	}
	
	
	//case 7
	public void fazerPedidoLanchonete() {
		exibirLanchonetes();
		System.out.print("Codigo da lanchonete a se fazer pedido: ");
		int codigo = s.nextInt();
		Lanchonete l = buscarLanchonete(codigo);
		if(l == null) {
			System.out.println("Codigo invalido!");
		}
		else {
			l.fazerPedido(this.getNome());
		}
	}

}
