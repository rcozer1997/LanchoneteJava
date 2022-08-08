package foodApp.Usuarios;

import java.util.ArrayList;
import java.util.Scanner;

import foodApp.App;
import foodApp.Sistema;

public class Administrador extends Usuario {
	Scanner s = new Scanner (System.in);
	
	public Administrador(Sistema sistema) {
		super(sistema);
		this.ident = 1;
	}

	public Administrador(ArrayList<String> list) {
		super(list);
	}

	public void menu() {
		int opcao;
		do {
		System.out.println("------------------------------------------------");
		System.out.println("              MENU ADMIN                        ");
		System.out.println("------------------------------------------------");
		System.out.println("1) Relatorio geral");
		System.out.println("2) Relatorio de vendas - nao funciona");
		System.out.println("3) Relatorio de desempenho - nao funciona");
		System.out.println("4) Remover cadastro");
		System.out.println("0) Sair");
		
		opcao = s.nextInt();
		switch(opcao) {
		case 1:
			relatorioGeral();
			break;
		case 4:
			removerCadastro(this.getEmail(), sistema.getListaUsuarios());
			App.main(null);
			break;	
		case 0:
			break;
		default:
			if(opcao == 0) {
				System.out.println("");
				}
			else System.out.println("Opcao invalida! Tente novamente.");
			break;
			}
		}while(opcao != 0);
	}
	
	public void relatorioGeral() {
		int totalClientes = retornaTotalClientes();
		System.out.println("Total de Clientes: " + totalClientes);
		System.out.println("Total de lanchonetes: " + sistema.getTodasLanchonetes().size());
		System.out.println("Total de lanches: " + sistema.getTodosLanches().size());
		System.out.println("Total de pedidos: "+ sistema.getTodosPedidos().size());
	}
	
	public int retornaTotalClientes() {
		int totalClientes = 0;
			for(Usuario c : sistema.getListaUsuarios()) {
				if(c.getIdent() == 3) {
					totalClientes++;
				}		
		}
		return totalClientes;
	}
}
