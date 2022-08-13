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
import foodApp.Lanchonetes.Lanchonete;
import foodApp.Lanchonetes.Pedidos;

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
		System.out.println("3) Relatorio de desempenho");
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
		case 3:
			relatorioDesempenho();
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
		System.out.println("------------------------------------LANCHES MAIS VENDIDOS---------------------------------------");
		lanchesMaisVendidos();
		System.out.println();
		System.out.println("---------------------------------LANCHES DE MAIOR FATURAMENTO-----------------------------------");
		lanchesMaiorFaturamento();
		System.out.println();
		System.out.println("------------------------------------LOJAS COM MAIS VENDAS---------------------------------------");
		lojasMaisVendas();
		System.out.println();
		System.out.println("----------------------------------LOJAS COM MAIOR FATURAMENTO-----------------------------------");
		lojasMaiorFaturamento();	
		System.out.println();
		System.out.println("----------------------------------CLIENTES QUE MAIS COMPRARAM-----------------------------------");
		clientesMaisCompras();
		System.out.println();
		System.out.println("----------------------------------CLIENTES QUE MAIS GASTARAM------------------------------------");
		clientesMaiorGasto();
		System.out.println();

	}
	
	public void relatorioDesempenho() {
		  for(Lanchonete l : sistema.getTodasLanchonetes()){
	            double qtdProdutos = 0;
	            double qtdPedidos = 0;
	            for(Pedidos pedido : l.getListaPedidos()){
	                qtdProdutos += pedido.getProdutos().size();
	                	if(pedido.getProdutos().size() == 0) qtdProdutos = 0;
	                qtdPedidos += pedido.getValorTotal();
	                	if(pedido.getValorTotal() == 0) qtdPedidos = 0;
	            }
	            System.out.println("Lanchonete: " + l.getNome());
	            if(qtdPedidos != 0 && qtdPedidos != 0)
	            System.out.println("Valor medio por produtos: " + qtdPedidos / qtdProdutos);
	            else System.out.println("Valor medio por produtos: 0.0");
	            
        		if(qtdProdutos != 0) 
    	            qtdProdutos /= l.getListaPedidos().size();	
        		if(qtdPedidos != 0)
    	            qtdPedidos /= l.getListaPedidos().size();
        		
        		System.out.println("Media de produtos por pedido: " + qtdProdutos);
	            System.out.println("Media de valor por pedido: " + qtdPedidos + '\n');
	            
	        }
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
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%20s", "CODIGO", "NOME", "PRECO", "VENDIDOS", "COD. LANCHONETE");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");
		if(sistema.getTodosLanches().size() >= 5) {
			for(int i = 0; i < 5; i++) {
				Lanche l = sistema.getTodosLanches().get(i);
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.format("%15s%20s%15s%15s%20s",l.getCodigo(), l.getNome(), l.getPreco(), l.getQntVendas(), l.getCodLanchonete());
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------");
			}		
		}
		else {
			for(Lanche l : sistema.getTodosLanches()) {
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.format("%15s%20s%15s%15s%20s",l.getCodigo(), l.getNome(), l.getPreco(), l.getQntVendas(), l.getCodLanchonete());
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------");
			}
		}
	}
	
	void exibeLanchonetes() {
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%15s%25s", "CODIGO", "NOME", "PROPRIETARIO", "ENDERECO", "VENDAS", "FATURAMENTO MEDIO");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");
		if(sistema.getTodasLanchonetes().size() >=5) {
			for(int i = 0; i < 5; i++) {
				Lanchonete l = sistema.getTodasLanchonetes().get(i);
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.format("%15s%20s%15s%15s%15s%15s",l.getCodigo(), l.getNome(), l.getProprietarioNome(), l.getEndereco(), l.getListaPedidos().size(), l.getPrecoMedio());
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------");
			}	
		}
		else {
			for(Lanchonete l : sistema.getTodasLanchonetes()) {
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.format("%15s%20s%15s%15s%15s%15s",l.getCodigo(), l.getNome(), l.getProprietarioNome(),l.getEndereco(), l.getListaPedidos().size(), l.getPrecoMedio());
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------");
			}
		}	
	}
	
	ArrayList<Cliente> getClientes(){   //Metodo a ser reaproveitado para que clientes possam ser filtrados na lista de usuarios
		ArrayList<Cliente> clientes = new ArrayList<>();
		for(Usuario u : sistema.getListaUsuarios()) {
			if(u.getIdent() == 3) {
				clientes.add((Cliente) u);
			}		
		}
		return clientes;
	}
	
	void exibeClientes() {
		ArrayList<Cliente> clientes = getClientes();
					
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("%15s%20s%15s", "NOME CLIENTE", "QNTD. COMPRAS", "GASTO TOTAL");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");
		
		if(clientes.size() >= 5) {
			for(int i = 0; i < 5; i++) {
				Cliente c = clientes.get(i);
				System.out.println("----------------------------------------------------------------------------------------------");
				System.out.format("%12s%15s%18s",c.getNome(), c.getQtdCompras(), c.gastoTotal());
				System.out.println();
				System.out.println("----------------------------------------------------------------------------------------------");
			}	
		}
		else {
			for(Cliente c : clientes) {
				System.out.println("----------------------------------------------------------------------------------------------");
				System.out.format("%12s%15s%18s",c.getNome(), c.getQtdCompras(), c.gastoTotal());
				System.out.println();
				System.out.println("----------------------------------------------------------------------------------------------");
			}
		}				
	}
}
