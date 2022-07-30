package foodApp.Usuarios;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Usuario {

	
	public Cliente() {
		super();
		this.ident = 3;
	}

	public Cliente(ArrayList<String> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void menu() {
		Scanner s = new Scanner (System.in);
		
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

		int opcao = s.nextInt();
		switch(opcao) {
		case 1:
			
			break;
			
		}
	}



}
