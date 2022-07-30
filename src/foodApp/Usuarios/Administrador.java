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
		
		System.out.println("------------------------------------------------");
		System.out.println("              MENU ADMIN                        ");
		System.out.println("------------------------------------------------");
		System.out.println("1) Relatorio geral");
		System.out.println("2) Relatorio de vendas");
		System.out.println("3) Relatorio de desempenho");
		System.out.println("4) Visualizar lista de pedidos de uma lanchonete");
		System.out.println("5) Visualizar um pedido de uma lanchonete");
		System.out.println("6) Remover pedido");
		System.out.println("7) Remover cadastro");
		
		int opcao = s.nextInt();
		switch(opcao) {
		case 1:
			
			break;
		case 7:
			removerCadastro(this.email, sistema.getListaUsuarios());
			break;		
		}
	}


}
