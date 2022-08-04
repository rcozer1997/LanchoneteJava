package foodApp.Usuarios;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuario {
	
	Sistema sistema = new Sistema();
	
	public Administrador(Sistema sistema) {
		super(sistema);
		this.ident = 1;
	}



	public Administrador(ArrayList<String> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void menu() {
		Scanner s = new Scanner (System.in);
		int opcao;
		do {
		System.out.println("------------------------------------------------");
		System.out.println("              MENU ADMIN                        ");
		System.out.println("------------------------------------------------");
		System.out.println("1) Relatorio geral");
		System.out.println("2) Relatorio de vendas");
		System.out.println("3) Relatorio de desempenho");
		System.out.println("4) Remover cadastro");
		System.out.println("0) Sair");
		
		opcao = s.nextInt();
		switch(opcao) {
		case 1:
			
			break;
		case 4:
			removerCadastro(this.getEmail(), sistema.getListaUsuarios());
			break;	
		case 0:
			break;
		default:
			System.out.println("Opcao invalida! Tente novamente.");
			break;
		}
		}while(opcao != 0);
	}
	
	public void relatorioGeral() {
		System.out.println("Total de Clientes: " + sistema.getListaUsuarios().size());
		System.out.println("Total de lanchonetes: " + sistema.getTodasLanchonetes().size());
		System.out.println("Total de lanches: " + sistema.getTodosLanches().size());
		System.out.println("Total de pedidos: "+ sistema.getTodosPedidos());
	}

}
