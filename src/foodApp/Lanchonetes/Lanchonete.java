package foodApp.Lanchonetes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import foodApp.Arquivos;
import foodApp.Usuarios.Proprietario;
import foodApp.Usuarios.Sistema;

public class Lanchonete {
	public int codigo;
	public String nome;
	public String endereco;
	public String categoria;
	public int pontos;
	String nomeProprietario;
	ArrayList<Lanche> lanches = new ArrayList<>();
	ArrayList<Pedidos> listaPedidos = new ArrayList<>();
	private double precoMedio;
	
	Sistema sistema = new Sistema();
	Arquivos arq = new Arquivos();
	Scanner s = new Scanner(System.in);
	@Override
	public String toString() {
		return "Lanchonete [codigo=" + codigo + ", nome=" + nome + ", endereco=" + endereco + ", categoria=" + categoria
				+ ", pontos=" + pontos + ", proprietario= " + nomeProprietario + "]";
	}

	public Lanchonete(BufferedReader b, Sistema sistema) {
		try {
		this.codigo = Integer.parseInt(b.readLine());
		this.nome = b.readLine();
		this.endereco = b.readLine();
		this.categoria = b.readLine();
		this.pontos = Integer.parseInt(b.readLine());
		this.nomeProprietario = b.readLine();
		this.sistema = sistema;
		}catch(IOException e) 
		{
			System.out.println("Erro");
		}
	}
	
	public Lanchonete(int codigo, String nome, String endereco, String categoria, String nomeProprietario) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.categoria = categoria;
		this.pontos = 0;
		this.nomeProprietario = nomeProprietario;
	}
	public void gravaLanchonete(BufferedWriter b) throws IOException {
			
			b.write(this.codigo + "\n");
			b.write(this.nome + "\n");
			b.write(this.endereco + "\n");
			b.write(this.categoria + "\n");
			b.write(this.pontos + "\n");
			b.write(this.nomeProprietario + "\n");
			
	}
		
	public void cadastraLanche(Lanchonete l) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Informe os dados do lanche:");
		System.out.println("Codigo identificador: ");
		int codigo = s.nextInt();
		s.nextLine();
		System.out.println("Descricao: ");
		String descricao = s.nextLine();
		System.out.println("Preco: ");
		float preco = s.nextFloat();
		
		if(verificaExistenciaLanche(codigo)) {
			Lanche lanche = new Lanche(codigo,descricao, preco, this.getNome());
			lanches.add(lanche);
			sistema.getTodosLanches().add(lanche);
			arq.salvaLanchesArq(sistema.getTodosLanches());
		}
	
		}
	
	public int getCodigo() {
		return this.codigo;
	}

	public String getProprietarioNome() {
		return this.nomeProprietario;
	}
	
	public ArrayList<Lanche> getListaLanches(){
		return this.lanches;
	}

	public void listarLanches() {
		System.out.println("------------------DADOS DA LANCHONETE---------------------------");
		System.out.println();
		System.out.printf("%20s%20s%15s%15s", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.format("%20s%20s%15s%15s",this.nome, this.endereco, this.categoria, this.pontos);
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("--------------------------LANCHES-----------------------------");
		System.out.println();
		System.out.printf("%15s%20s%15s", "CODIGO", "DESCRICAO", "PRECO");
		System.out.println();
		for(Lanche l : lanches) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s",l.codigo, l.descricao, l.preco);
			System.out.println();
			System.out.println("---------------------------------------------------------");
			
		}
	}
	
	
	public boolean verificaExistenciaLanche(int codigo) {
		for(int i = 0; i<lanches.size();i++){
			Lanche l = lanches.get(i);
			if(l.getCodigo() == codigo){
				System.out.println("Lanche com este codigo ja esta cadastrado!");
				return false;
			}
		}
		return true;
	}
	
	public void visualizarPedidos() {
		
		System.out.println("---------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%20s", "CODIGO", "NOME CLIENTE", "VALOR TOTAL", "QTD. ITENS", "DATA");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		for(Pedidos p : listaPedidos) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s%20s",p.codigo, p.nomeCliente, p.valorTotal, p.qntItens, p.data);
			System.out.println();
			System.out.println("---------------------------------------------------------");
			}
		System.out.println("Numero de pedidos: " + this.listaPedidos.size());

	}
	
	public void visualizarPedidoEspecifico() {
		visualizarPedidos();
		System.out.println("Codigo do pedido a ser visto: ");
		int codigo = s.nextInt();
		Pedidos p = buscarPedido(codigo);
		if(p == null) {
			System.out.println("Codigo invalido!");
		}
		else {	
			System.out.println("---------------------------------------------------------");
			System.out.printf("%15s%20s%15s%15s%20s", "CODIGO", "NOME CLIENTE", "DATA", "PRODUTOS");
			System.out.println();
			System.out.println("---------------------------------------------------------");
				System.out.println("---------------------------------------------------------");
				System.out.format("%15s%20s%15s%15s%20s",p.codigo, p.nomeCliente, p.data, p.produtos);
				System.out.println();
				System.out.println("---------------------------------------------------------");				
		}
	
	}
	
	public Pedidos buscarPedido(int codigo) {
		for(Pedidos p : listaPedidos)
			if(p.codigo == codigo)
				return p;
		return null;
	}
	
	public ArrayList<Lanche> getLanches(){
		return lanches;
	}
	
	public ArrayList<Pedidos> getListaPedidos(){
		return listaPedidos;
	}
	
	public double getPrecoMedio() {
		return precoMedio;
	}
	
	public int comparaPontos(Lanchonete l) {
		if(this.pontos < l.pontos) return -1;
		if(this.pontos > l.pontos) return 1;
		return 0;
	}
	
	public void precoMedioLanches() {
		double soma = 0;
		for(int i=0; i<lanches.size(); i++)
			soma += lanches.get(i).getPreco();
		this.precoMedio = (soma/lanches.size());
	}
	
	public int compareTo(Lanchonete l) {
		if(this.precoMedio < l.precoMedio) return -1;
		if(this.precoMedio > l.precoMedio) return 1;
		return 0;
	}
	
	public void fazerPedido(String nomeCliente) {
		int opcao;
		listarLanches();
		System.out.println("Digite o codigo do produto: ");
		opcao = s.nextInt();
		double valorTotal = 0;
		int qntItens = 0;
		Pedidos p = new Pedidos(codigo, nomeCliente, valorTotal, qntItens, this.getNome());
		listaPedidos.add(p);
		//Pedido p = new Pedido();
		//listaPedidos.add(p);
	}
	
	public void removePedido(Pedidos p) {
	
		listaPedidos.remove(p);
	}

	public String getNome() {
		return this.nome;
	}
}

	

