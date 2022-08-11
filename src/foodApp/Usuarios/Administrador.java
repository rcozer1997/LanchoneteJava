package foodApp.Usuarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import foodApp.App;
import foodApp.Sistema;
import foodApp.Compara.ComparaClienteCompras;
import foodApp.Compara.ComparaClienteGasto;
import foodApp.Compara.ComparaLanchePreco;
import foodApp.Compara.ComparaLanchesVendidos;
import foodApp.Compara.ComparaLanchonetePrecoMedio;
import foodApp.Compara.ComparaLanchoneteVendas;
import foodApp.Lanchonetes.Lanche;

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
		System.out.println("2) Relatorio de vendas");
		System.out.println("3) Relatorio de desempenho - nao funciona");
		System.out.println("4) Remover cadastro");
		System.out.println("0) Sair");
		
		opcao = s.nextInt();
		switch(opcao) {
		case 1:
			relatorioGeral();
			break;
		case 2:
			relatorioVendas();
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
	
	public void relatorioVendas() {
		lanchesMaisVendidos();
		System.out.println("");
		lanchesMaiorFaturamento();
		System.out.println("");
		lojasMaisVendas();
		System.out.println("");
		lojasMaiorFaturamento();	
		System.out.println("");
		clientesMaisCompras();
		System.out.println("");
		clientesMaiorGasto();
	}
	
	void lanchesMaisVendidos() {
		ComparaLanchesVendidos c = new ComparaLanchesVendidos();
		Collections.sort(sistema.getTodosLanches(), c);
		exibeLanches();
	}
	
	void lanchesMaiorFaturamento() {
		ComparaLanchePreco c = new ComparaLanchePreco();
		Collections.sort(sistema.getTodosLanches(), c);
		exibeLanches();	
	}
	
	void lojasMaisVendas() {
		ComparaLanchoneteVendas c = new ComparaLanchoneteVendas();
		Collections.sort(sistema.getTodasLanchonetes(), c);
		exibeLanchonetes();	
	}
	
	void lojasMaiorFaturamento() {
		ComparaLanchonetePrecoMedio c = new ComparaLanchonetePrecoMedio();
		Collections.sort(sistema.getTodasLanchonetes(), c);
		exibeLanchonetes();
	}
	
	void clientesMaisCompras() {
		ArrayList<Cliente> clientes = getClientes();
		ComparaClienteCompras c = new ComparaClienteCompras();
		Collections.sort(clientes, c);
		exibeClientes();
	}
	
	void clientesMaiorGasto() {
		ArrayList<Cliente> clientes = getClientes();
		ComparaClienteGasto c = new ComparaClienteGasto();
		Collections.sort(clientes, c);
		exibeClientes();
	}
	
	void exibeLanches() {
		if(sistema.getTodosLanches().size() >= 5) {
			for(int i = 0; i < 5; i++) {
				System.out.println(sistema.getTodosLanches().get(i));
			}		
		}
		else System.out.println(sistema.getTodosLanches());	
	}
	
	void exibeLanchonetes() {
		if(sistema.getTodasLanchonetes().size() >=5) {
			for(int i = 0; i < 5; i++) {
				System.out.println(sistema.getTodasLanchonetes().get(i));
			}	
		}
		else System.out.println(sistema.getTodasLanchonetes());
		
	}
	
	ArrayList<Cliente> getClientes(){
		ArrayList<Cliente> clientes = new ArrayList<>();
		for(Usuario u : sistema.getListaUsuarios()) {
			if(u.getIdent() == 3) {
				clientes.add((Cliente) u);
			}		
		}
		return clientes;
	}
	
	void exibeClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		for(Usuario c : sistema.getListaUsuarios()) {
			if(c.getIdent() == 3) {
				clientes.add((Cliente) c);
			}		
		}		
		if(clientes.size() >= 5) {
			for(int i = 0; i < 5; i++) {
				System.out.println(clientes.get(i));
			}	
		}
		else System.out.println(clientes);				
	}
}
