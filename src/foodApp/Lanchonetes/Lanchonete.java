package foodApp.Lanchonetes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import foodApp.Arquivos;
import foodApp.Sistema;
import foodApp.Exceptions.CodigoReplicadoException;

public class Lanchonete {
	public int codigo;
	public String nome;
	public String endereco;
	public String categoria;
	public float pontos;
	String nomeProprietario;
	private String emailProprietario;
	ArrayList<Lanche> lanches = new ArrayList<>();
	ArrayList<Pedidos> listaPedidos = new ArrayList<>();
	private double precoMedio;
	
	Sistema sistema = new Sistema();
	Arquivos arq = new Arquivos();
	Scanner s = new Scanner(System.in);
	
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
		this.pontos = Float.parseFloat(b.readLine());
		this.nomeProprietario = b.readLine();
		this.emailProprietario = b.readLine()
;		this.sistema = sistema;
		}catch(IOException e) 
		{
			System.out.println("Erro");
		}
	}
	
	public Lanchonete(int codigo, String nome, String endereco, String categoria, String nomeProprietario, String emailProprietario) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.categoria = categoria;
		this.pontos = 0;
		this.nomeProprietario = nomeProprietario;
		this.emailProprietario = emailProprietario;
	}
	public void gravaLanchonete(BufferedWriter b) throws IOException {		
			b.write(this.codigo + "\n");
			b.write(this.nome + "\n");
			b.write(this.endereco + "\n");
			b.write(this.categoria + "\n");
			b.write(this.pontos + "\n");
			b.write(this.nomeProprietario + "\n");
			b.write(this.emailProprietario + "\n");
	}
		
	public void cadastraLanche(Lanchonete l) throws CodigoReplicadoException {		
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

		}
		else if(!verificaExistenciaLanche(codigo)) {
			throw new CodigoReplicadoException("Codigo ja cadastrado!");
		}

	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public String getProprietarioNome() {
		return this.nomeProprietario;
	}
	
	public String getProprietarioEmail() {
		return this.emailProprietario;
	}
	public ArrayList<Lanche> getListaLanches(){
		return this.lanches;
	}
	
	public void removeTodosPedidos() {
		for(Pedidos thisPedido : listaPedidos) {
			for(int i = 0; i < sistema.getTodosPedidos().size(); i++) {
				Pedidos p = sistema.getTodosPedidos().get(i);
				if(p.getCodigo() == thisPedido.getCodigo()) {
					sistema.getTodosPedidos().remove(p);
				}
			}
		}		
		//listaPedidos.removeAll(listaPedidos);
	}
	
	public void removeTodosLanches() {
		for(Lanche thisLanches : lanches) {
			for(int i = 0; i < sistema.getTodosLanches().size(); i++) {
				Lanche l = sistema.getTodosLanches().get(i);
				if(l.getCodigo() == thisLanches.getCodigo()) {
					sistema.getTodosLanches().remove(l);
				}
			}
		}
		//lanches.removeAll(lanches);
	}
	
	public void listarLanches() {
		System.out.println("-------------------------------DADOS DA LANCHONETE-----------------------------------------");
		System.out.println();
		System.out.printf("%20s%20s%15s%15s", "NOME", "ENDERECO", "CATEGORIA", "PONTOS");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.format("%20s%20s%15s%15s",this.nome, this.endereco, this.categoria, this.pontos);
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("-------------------------------------LANCHES------------------------------------------------");
		System.out.println();
		System.out.printf("%15s%20s%15s", "CODIGO", "DESCRICAO", "PRECO");
		System.out.println();
		for(Lanche l : lanches) {
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.format("%15s%20s%15s",l.codigo, l.descricao, l.preco);
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------");
			
		}
	}
		
	public boolean verificaExistenciaLanche(int codigo) {
		for(int i = 0; i<lanches.size();i++){
			Lanche l = sistema.getTodosLanches().get(i);
			if(l.getCodigo() == codigo){
				return false;
			}
		}
		return true;
	}
	
	public void visualizarPedidos() {
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s%20s", "CODIGO", "NOME CLIENTE", "VALOR TOTAL", "QTD. ITENS", "DATA");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");
		for(Pedidos p : listaPedidos) {
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.format("%15s%20s%15s%10s%40s",p.codigo, p.nomeCliente, p.valorTotal, p.qntItens, p.data);
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------------------");
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
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.printf("%15s%20s%15s%15s%20s", "CODIGO", "NOME CLIENTE", "DATA", "PRODUTOS");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.format("%15s%20s%15s%15s%20s",p.codigo, p.nomeCliente, p.data, p.produtos);
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------");
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
		if(this.pontos < l.pontos) return 1;
		if(this.pontos > l.pontos) return -1;
		return 0;
	}
	
	public int comparaVendas(Lanchonete l) {
		if(this.listaPedidos.size() < l.listaPedidos.size()) return -1;
		if(this.listaPedidos.size() > l.listaPedidos.size()) return 1;
		return 0;
	}
	
	
	public void precoMedioLanches() {
		double soma = 0;
		for(int i=0; i<lanches.size(); i++) {
			soma += lanches.get(i).getPreco();
		}
		this.precoMedio = (soma/lanches.size());
	}
	
	public int compareTo(Lanchonete l) {
		if(this.precoMedio < l.precoMedio) return 1;
		if(this.precoMedio > l.precoMedio) return -1;
		return 0;
	}
	
	public void fazerPedido(String nomeCliente, String emailCliente) {
		int opcao;	
		int quant;
		float valor;
		ArrayList<Lanche> produtosComprados = new ArrayList<>();
		LocalDateTime dataAtual = LocalDateTime.now();
		do {
			listarLanches();
			quant = 0;
			valor = 0;
		System.out.println("Digite o codigo do produto ou pressione 0 para sair: ");
		opcao = s.nextInt();
		if(opcao == 0) {
			System.out.println("Obrigado! Volte sempre!");
			break;
		}
		else if(opcao != 0) {
			for(Lanche l : lanches) {
				if(l.getCodigo() == opcao) {
					System.out.println("Qual a quantidade?");
					quant = s.nextInt();
					for(int i = 0; i < quant; i++) {
						produtosComprados.add(l);
						
					}
					valor = l.getPreco()*quant;
					for(Lanche lancheSistema : sistema.getTodosLanches()) {
						if(lancheSistema.getCodigo() == l.getCodigo()) {
							l.atualizaqntVendas(quant);
							lancheSistema.atualizaqntVendas(quant);
					}
				}
			}		
		}
	}		
			/*
			else {
				System.out.println("Codigo invalido!");
			}*/		
		/*
		System.out.println("Carrinho atual: ");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%15s%20s%15s%15s", "CODIGO", "NOME", "QUANT.", "VALOR");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		for(Lanche comprado : produtosComprados) {
			System.out.println("---------------------------------------------------------");
			System.out.format("%15s%20s%15s%15s",comprado.getCodigo(), comprado.getNome(), quant, valor);
			System.out.println();
			System.out.println("---------------------------------------------------------");
		}*/		
		}while( opcao != 0);
		
		int codigoPedido = geraCodigoPedido(); //Optei por gerar randomicamente o codigo do pedido, checando tambem se o numero randomizado ja esta cadastrado. Caso sim, randomiza novamente 
		int qntItens = produtosComprados.size();
		double valorTotal = calculaValorTotalPedido(produtosComprados);
		Pedidos p = new Pedidos(codigoPedido, produtosComprados, nomeCliente, emailCliente, valorTotal, qntItens, this.getNome(), dataAtual);
		listaPedidos.add(p);
		sistema.getTodosPedidos().add(p);
		/*if(p.getQntItens() == 0) {     //Isso foi feito de modo a tratar um problema de que, ao selecionar 0 para sair, o codigo criava pedidos com valores 0. Esse trecho trata e remove tais pedidos
			listaPedidos.remove(p);  
			sistema.getTodosPedidos().remove(p);
		}*/
	}
	
	public void removePedido(Pedidos p) {
		listaPedidos.remove(p);
		sistema.getTodosPedidos().remove(p);
		//arq.salvaPedidosArq(sistema.getTodosPedidos());
	}

	public String getNome() {
		return this.nome;
	}
	
	public void atualizaPontos(float nota) {
		this.pontos = (pontos + nota)/2;
	}
	
	public int geraCodigoPedido() {
		int codigoPedido =  new Random().nextInt(10000) + 1;
			for(Pedidos p : sistema.getTodosPedidos()) {
				if(p.getCodigo() == codigoPedido) {
					codigoPedido = new Random().nextInt(10000) + 1;
				}
			}
		if(codigoPedido == 0) {
			codigoPedido++;
		}
		return codigoPedido;
	}
	
	public double calculaValorTotalPedido(ArrayList<Lanche> lanchesComprados) {
		double valorTotal = 0;
		for(Lanche l : lanchesComprados) {
			valorTotal = valorTotal + l.getPreco();
		}
		return valorTotal;
	}
}

	

