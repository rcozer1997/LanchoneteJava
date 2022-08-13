package foodApp.Lanchonetes;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import foodApp.Sistema;
import foodApp.Exceptions.CodigoNaoEncontradoException;

public class Pedidos {
	public Sistema sistema;
	private int codigo;
	private String nomeCliente;
	private String emailCliente;
	private double valorTotal;
	private int qntItens;
	private int codLanchonete;
	private LocalDateTime data;
	private ArrayList<Lanche> produtos = new ArrayList<>();

	public String toString() {
        return String.valueOf('\n' + "<Pedido>" +
                                " Codigo da Lanchonete: " + codLanchonete + ", " +
                                "Codigo do Pedido: " + codigo + ", " +
                                "Nome do Cliente: " + nomeCliente + ", " +
                                "Data: " + data + "\n" +
                                "<Lanches> " + produtos.toString() + "\n" +
        						"Valor Total: " + valorTotal + '\n' + '\n');
        						

    }
	public Pedidos(int codigo, ArrayList<Lanche> produtos, String nomeCliente, String emailCliente, double valorTotal, int qntItens, int codLanchonete, LocalDateTime data, Sistema sistema) {
		super();
		this.codigo = codigo;
		this.produtos = produtos;
		this.nomeCliente = nomeCliente;
		this.emailCliente = emailCliente;
		this.valorTotal = valorTotal;
		this.qntItens = qntItens;
		this.data = data;
		this.codLanchonete = codLanchonete;
		this.sistema = sistema;
	}
	
	public Pedidos(ArrayList<String> list, Sistema sistema) {		
		this.codigo = Integer.parseInt(list.get(0));
		this.nomeCliente = list.get(1);
		this.emailCliente = list.get(2);
		this.valorTotal = Double.parseDouble(list.get(3));
		this.qntItens = Integer.parseInt(list.get(4));
		this.data = LocalDateTime.parse(list.get(5));
		this.codLanchonete = Integer.parseInt(list.get(6));
		String[] stringCodigoLanches = list.get(7).split(";");
		ArrayList<Integer> codigosDecodificados = new ArrayList<>();
			for(String parte : stringCodigoLanches) {
				int parteDecodificada = Integer.parseInt(parte);
				codigosDecodificados.add(parteDecodificada);
			}			
			for(int codigoLanche : codigosDecodificados) {
				for(Lanche l : sistema.getTodosLanches()) {
					if(l.getCodigo() == codigoLanche) 
						produtos.add(l);	 
				}
			}
		this.sistema = sistema;
	}

	public void gravaPedido(BufferedWriter b) throws IOException {		
		b.write(this.codigo + "\n");
		b.write(this.nomeCliente + "\n");
		b.write(this.emailCliente + "\n");
		b.write(this.valorTotal + "\n");
		b.write(this.qntItens + "\n");
		b.write(this.data + "\n");
		b.write(this.codLanchonete + "\n");
		for(int i = 0; i < produtos.size(); i++) {
			Lanche l = produtos.get(i);
			b.write(l.getCodigo() + ";");
		}
		b.write("\n");
}
	public int getCodigo() {
		return codigo;
	}
	
	public int getQntItens() {
		return qntItens;
	}
	
	public String getNomeLanchonete() {
		String nomeLanchonete = null;
		for(Lanchonete l : sistema.getTodasLanchonetes()) {
			if(l.getCodigo() == this.codLanchonete) {
				nomeLanchonete = l.getNome();
			}
		}
		return nomeLanchonete;
	}
	public void getProdutosRelatorio() {
		for(Lanche l : this.produtos) {
			System.out.println("Produto: " + l.getNome() + " ,Valor Unitario: " + l.getPreco());
		}
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public LocalDateTime getData() {
		return data;
	}
	
	public ArrayList<Lanche> getProdutos(){
		return produtos;
	}
	
	public int somaProdutos() {
		return produtos.size();
	}
	
	public double valorMedioProdutos() {
		double soma = 0;
		for(Lanche l : produtos) {
			soma += l.getPreco();
		}
		return soma;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
}

